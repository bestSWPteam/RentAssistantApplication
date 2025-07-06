package com.example.rentassistantapp

import android.content.Context
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
import com.example.rentassistantapp.ui.theme.RentAssistantAppTheme
import com.example.rentassistantapp.ui.welcome.WelcomeScreen
import com.example.rentassistantapp.util.Config
import com.example.rentassistantapp.util.PrefsHelper
import com.example.rentassistantapp.util.pollUntilPaid

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fakeJwt = "your-fake-jwt-token-here"
        if (PrefsHelper.getJwt(applicationContext).isNullOrBlank()) {
            PrefsHelper.saveJwt(applicationContext, fakeJwt)
            Log.d("DEBUG", "Saved temporary fake JWT")
        }

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
                            onLogin = { /*startTelegramLogin()*/ },
                            onDocsClick = { openLink("https://user-agreement.pdf") }
                        )
                    }
                    composable("profile") {
                        UsersScreen(
                            surname = "",
                            name = PrefsHelper.getFirstName(applicationContext) ?: "",
                            subscriptionType = PrefsHelper.getSubscriptionType(applicationContext) ?: "Нет подписки",
                            subscriptionStatus = if (PrefsHelper.getJwt(applicationContext).isNullOrBlank()) "Неактивна" else "Активна",
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
                            onDocumentation = { openLink("https://user-agreement.pdf") },
                            onTelegramBot = { openLink("https://t.me/${Config.TELEGRAM_BOT_ID}") },
                            onAboutUs = { openLink("https://ex.com/about") },
                            onDeleteAccount = {
                                PrefsHelper.clearAll(applicationContext)
                                PrefsHelper.resetLaunchFlag(applicationContext)
                                navController.navigate("welcome") {
                                    popUpTo(0)
                                }
                            },
                            onTasksClick = { navController.navigate("tasks") },
                            onProfileClick = { navController.navigate("profile") },
                            isProfileSelected = true
                        )
                    }
                    composable("subscription") {
                        SubscriptionChoosingScreen(
                            onPlanSelected = { plan, hours -> navController.navigate("confirm/$plan/$hours") },
                            onBack = { navController.navigate("profile") }
                        )
                    }
                    composable(
                        "confirm/{plan}/{hours}",
                        arguments = listOf(
                            navArgument("plan") { type = NavType.StringType },
                            navArgument("hours") { type = NavType.IntType }
                        )
                    ) { back ->
                        val plan = back.arguments!!.getString("plan")!!
                        val hours = back.arguments!!.getInt("hours")
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
                    composable("success") {
                        SuccessPurchaseScreen(onClose = {
                            navController.navigate("profile") {
                                popUpTo("profile") { inclusive = true }
                            }
                        })
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

// --- Telegram OAuth Methods (Currently Disabled) ---

    /*
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        Log.e("DEEPLINK", "onNewIntent triggered with intent.data=${intent.data}")
        lifecycleScope.launch {
            handleDeepLink(intent)
        }
    }

    private suspend fun handleDeepLink(intent: Intent) {
        Log.i("DEEPLINK", "got intent.data=${intent.data}")
        intent.data?.let { uri ->
            if (uri.scheme == "myapp" && uri.host == "auth-callback") {
                val userId = uri.getQueryParameter("id") ?: return
                val firstName = uri.getQueryParameter("first_name") ?: return
                val username = uri.getQueryParameter("username") ?: return
                val authDate = uri.getQueryParameter("auth_date") ?: return
                val hash = uri.getQueryParameter("hash") ?: return

                val authApi = NetworkModule.provideAuthApi(this@MainActivity)
                val resp = authApi.loginWithTelegram(
                    TelegramLoginRequest(userId, firstName, username, authDate, hash)
                )

                if (resp.isSuccessful) {
                    val jwt = resp.body()!!.token
                    PrefsHelper.saveJwt(applicationContext, jwt)
                    PrefsHelper.saveTelegramUser(applicationContext, userId, firstName, username)

                    val restartIntent = Intent(this@MainActivity, MainActivity::class.java)
                    restartIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(restartIntent)
                    finish()
                } else {
                    Log.e("Auth", "Login failed: ${resp.code()} ${resp.errorBody()?.string()}")
                }
            }
        }
    }

    private fun startTelegramLogin() {
        val url = buildTelegramAuthUrl(
            botId = Config.TELEGRAM_BOT_ID,
            redirect = Config.TELEGRAM_REDIRECT,
            origin = Config.TELEGRAM_ORIGIN
        )
        Log.d("OAUTH_URL", "Telegram auth url: $url")
        CustomTabsIntent.Builder().build().apply {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }.launchUrl(this, Uri.parse(url))
    }
    */
}
