package com.example.rentassistantapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.browser.customtabs.CustomTabsIntent
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rentassistantapp.data.model.PaymentRequest
import com.example.rentassistantapp.di.NetworkModule
import com.example.rentassistantapp.ui.login.StartingScreen
import com.example.rentassistantapp.ui.profile.UsersScreen
import com.example.rentassistantapp.ui.subscription.SubscriptionChoosingScreen
import com.example.rentassistantapp.ui.subscription.SubscriptionConfirmationScreen
import com.example.rentassistantapp.ui.subscription.SuccessPurchaseScreen
import com.example.rentassistantapp.ui.tasks.TasksScreen
import com.example.rentassistantapp.ui.theme.RentAssistantAppTheme
import com.example.rentassistantapp.ui.welcome.WelcomeScreen
import com.example.rentassistantapp.util.Config
import com.example.rentassistantapp.util.PrefsHelper
import com.example.rentassistantapp.util.pollUntilPaid
import kotlinx.coroutines.delay
import androidx.lifecycle.lifecycleScope
import com.example.rentassistantapp.ui.profile.AboutUsScreen
import com.example.rentassistantapp.ui.profile.DocumentsScreen
import com.example.rentassistantapp.ui.profile.TaskExamplesScreen
import kotlinx.coroutines.launch
import com.example.rentassistantapp.ui.profile.PERSONAL
import com.example.rentassistantapp.ui.profile.BUSINESS

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val fakeJwt = "your-fake-jwt-token-here" // WILL BE DELETED
//        if (PrefsHelper.getJwt(applicationContext).isNullOrBlank()) {
//            PrefsHelper.saveJwt(applicationContext, fakeJwt)
//            Log.d("DEBUG", "Saved temporary fake JWT")
//        }

        setContent {
            RentAssistantAppTheme {
                val navController = rememberNavController()
                val jwt = PrefsHelper.getJwt(applicationContext)
                val startDest = if (!jwt.isNullOrBlank()) {
                    "profile"
                } else if (PrefsHelper.isFirstLaunch(applicationContext)) {
                    "welcome"
                } else {
                    "start"
                }

                NavHost(navController = navController, startDestination = startDest) {
                    composable("welcome") {
                        WelcomeScreen(onContinue = {
                            PrefsHelper.markLaunched(applicationContext)
                            navController.navigate("start") {
                                popUpTo("welcome") { inclusive = true }
                            }
                        })
                    }
                    composable("start") {
                        StartingScreen(
                            onLogin = { startTelegramLogin() },
                            onDocsClick = { navController.navigate("document") }
                        )
                    }
                    composable("profile") {
                        val subType = PrefsHelper.getSubscriptionType(applicationContext) ?: "Нет подписки"
                        val isActive = subType != "Нет подписки"
                        val subStatus = if (isActive) "Активна" else "Неактивна"

                        UsersScreen(
                            surname = PrefsHelper.getLastName(applicationContext) ?: "—",
                            name = PrefsHelper.getFirstName(applicationContext) ?: "—",
                            subscriptionType = subType,
                            subscriptionStatus = subStatus,
                            expireDate = PrefsHelper.getExpireDate(applicationContext) ?: "—",
                            onChangeProfile = {
                                PrefsHelper.clearAll(applicationContext)
                                PrefsHelper.resetLaunchFlag(applicationContext)
                                navController.navigate("welcome") {
                                    popUpTo(0)
                                }
                            },
                            onUpgrade = { navController.navigate("subscription") },
                            onProlong = { navController.navigate("subscription") },
                            onManageSubscriptions = { navController.navigate("subscription") },
                            onSupport = { openLink("https://support.com") },
                            onDocumentation = { navController.navigate("document") },
                            onTelegramBot = { openLink("https://t.me/${Config.TELEGRAM_BOT_ID}") },
                            onAboutUs = { openLink("https://ex.com/about") },
                            onDeleteAccount = {
                                PrefsHelper.clearAll(applicationContext)
                                PrefsHelper.resetLaunchFlag(applicationContext)
                                navController.navigate("welcome") {
                                    popUpTo(0)
                                }
                            },
                            onTasksExamples = { navController.navigate("personal_tasks_examples") },
                            navController = navController
                        )
                    }

                    composable("subscription") {
                        SubscriptionChoosingScreen(
                            onPlanSelected = { plan ->
                                val defaultHours = 2
                                navController.navigate("confirm/$plan/$defaultHours")
                            },
                            onBack = { navController.navigate("profile") }
                        )
                    }

                    composable("personal_tasks_examples") {
                        TaskExamplesScreen(
                            heading = "Личные задачи",
                            content = PERSONAL,
                            navController = navController
                        )
                    }

                    composable("business_tasks_examples") {
                        TaskExamplesScreen(
                            heading = "Бизнес задачи",
                            content = BUSINESS,
                            navController = navController
                        )
                    }
                    /*composable(
                        "select-hours/{plan}",
                        arguments = listOf(navArgument("plan") { type = NavType.StringType })
                    ) { backStack ->
                        val plan = backStack.arguments?.getString("plan") ?: return@composable
                        SubscriptionHoursScreen(
                            plan = plan,
                            onHoursSelected = { hours ->
                                navController.navigate("confirm/$plan/$hours")
                            },
                            onBack = { navController.popBackStack() }
                        )
                    }*/
                    composable(
                        "confirm/{plan}/{hours}",
                        arguments = listOf(
                            navArgument("plan") { type = NavType.StringType },
                            navArgument("hours") { type = NavType.IntType }
                        )
                    ) { backStack ->
                        val plan = backStack.arguments!!.getString("plan")!!
                        val hours = backStack.arguments!!.getInt("hours")
                        val cost = when (plan to hours) {
                            "Лайт" to 2 -> "15 000 ₽"
                            "Лайт" to 5 -> "30 000 ₽"
                            "Лайт" to 8 -> "50 000 ₽"
                            "Бизнес" to 2 -> "30 000 ₽"
                            "Бизнес" to 5 -> "60 000 ₽"
                            "Бизнес" to 8 -> "80 000 ₽"
                            "Экстра" to 2 -> "40 000 ₽"
                            "Экстра" to 5 -> "80 000 ₽"
                            "Экстра" to 8 -> "100 000 ₽"
                            else -> ""
                        }
                        SubscriptionConfirmationScreen(
                            subscriptionType = plan,
                            cost = cost,
                            onEnter = { ctx -> launchPayment(plan, hours, navController, ctx) },
                            onBack = { navController.popBackStack() }
                        )
                    }
                    composable("tasks") {
                        val subType = PrefsHelper.getSubscriptionType(applicationContext)
                        val isSubscription = !subType.isNullOrBlank() && subType != "Нет подписки"
                        TasksScreen(
                            isSubscription = isSubscription,
                            onGoToSubscription = { navController.navigate("subscription") },
                            navController = navController,
                            onFilter = {}
                        )
                    }
                    composable("success") {
                        SuccessPurchaseScreen(onClose = {
                            navController.navigate("profile") {
                                popUpTo("profile") { inclusive = true }
                            }
                        })
                    }
                    composable("document") {
                        DocumentsScreen()
                    }
                    composable("about_us") {
                        AboutUsScreen()
                    }
                }
            }
        }
    }

    private suspend fun launchPayment(plan: String, hours: Int, nav: NavController, context: Context) {
        val jwt = PrefsHelper.getJwt(context)
        if (jwt.isNullOrBlank()) {
            Log.e("PAY", "JWT is null, can't create payment")
            return
        }

        val userId = PrefsHelper.getTelegramId(context)
        if (userId.isNullOrBlank()) {
            Log.e("PAY", "Telegram ID is null")
            return
        }

        val amount = when (plan to hours) {
            "Лайт" to 2 -> 15_000
            "Лайт" to 5 -> 30_000
            "Лайт" to 8 -> 50_000
            "Бизнес" to 2 -> 30_000
            "Бизнес" to 5 -> 60_000
            "Бизнес" to 8 -> 80_000
            "Экстра" to 2 -> 40_000
            "Экстра" to 5 -> 80_000
            "Экстра" to 8 -> 100_000
            else -> {
                Log.e("PAY", "Unknown plan/hours: $plan/$hours")
                return
            }
        }

        val planKey = when (plan) {
            "Лайт" -> "light"
            "Бизнес" -> "business"
            "Экстра" -> "extra"
            else -> {
                Log.e("PAY", "Invalid plan: $plan")
                return
            }
        }

        val hrsKey = when (hours) {
            2 -> "two"
            5 -> "five"
            8 -> "eight"
            else -> {
                Log.e("PAY", "Invalid hours: $hours")
                return
            }
        }

        val txnId = java.util.UUID.randomUUID().toString()
        val request = PaymentRequest(
            user_telegram_id = userId,
            plan = planKey,
            plan_hrs = hrsKey,
            amount = amount,
            payment_txn_id = txnId
        )

        val api = NetworkModule.providePaymentApi(context)
        val resp = api.createPayment(jwt, request)

        if (resp.isSuccessful) {
            val (url, id) = resp.body()!!
            CustomTabsIntent.Builder().build().launchUrl(context, Uri.parse(url))
            pollUntilPaid(api::getPaymentStatus, id)
            nav.navigate("success")
        } else {
            Log.e("PAY", "Ошибка при создании платежа: ${resp.code()} ${resp.errorBody()?.string()}")
        }
    }

    private fun openLink(url: String) {
        CustomTabsIntent.Builder()
            .build()
            .launchUrl(this, Uri.parse(url))
    }

    private fun startTelegramLogin() {
        val retrofit = NetworkModule.provideAuthApi(this)
        lifecycleScope.launch {
            try {
                val codeResp = retrofit.prepareLogin()
                val code = codeResp.code

                val url = "https://t.me/${Config.TELEGRAM_BOT_ID}?start=$code"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)

                pollLoginResult(code)
            } catch (e: Exception) {
                Log.e("TG_LOGIN", "Ошибка при старте логина", e)
            }
        }
    }


    private suspend fun pollLoginResult(code: String) {
        val authApi = NetworkModule.provideAuthApi(this)
        repeat(30) {
            delay(2000)
            val resp = authApi.checkLoginStatus(code)
            if (resp.isSuccessful) {
                val body = resp.body()!!
                body.token?.let { token ->
                    PrefsHelper.saveJwt(applicationContext, token)
                    PrefsHelper.saveTelegramUser(applicationContext, body.id, body.firstName, body.username)
                    restartToProfile()
                }
                return
            }
        }
        Log.e("TG_LOGIN", "Не удалось авторизоваться")
    }


    private fun restartToProfile() {
        val intent = Intent(this@MainActivity, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

}
