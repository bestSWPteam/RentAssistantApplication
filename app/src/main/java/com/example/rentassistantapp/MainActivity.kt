package com.example.rentassistantapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rentassistantapp.di.NetworkModule
import com.example.rentassistantapp.data.model.TelegramLoginRequest
import com.example.rentassistantapp.data.model.PaymentRequest
import com.example.rentassistantapp.ui.login.StartingScreen
import com.example.rentassistantapp.ui.profile.UsersScreen
import com.example.rentassistantapp.ui.subscription.SubscriptionChoosingScreen
import com.example.rentassistantapp.ui.subscription.SubscriptionConfirmationScreen
import com.example.rentassistantapp.ui.subscription.SuccessPurchaseScreen
import com.example.rentassistantapp.ui.welcome.WelcomeScreen
import com.example.rentassistantapp.ui.theme.RentAssistantAppTheme
import com.example.rentassistantapp.util.Config
import com.example.rentassistantapp.util.PrefsHelper
import com.example.rentassistantapp.util.buildTelegramAuthUrl
import com.example.rentassistantapp.util.pollUntilPaid
import kotlinx.coroutines.launch
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf

class MainActivity : ComponentActivity() {
    private val fromOAuthState = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleDeepLink(intent)

        setContent {
            RentAssistantAppTheme {
                val nav = rememberNavController()

                LaunchedEffect(fromOAuthState) {
                    if (fromOAuthState.value) {
                        nav.navigate("profile") {
                            popUpTo(0)
                        }
                        fromOAuthState.value = false
                    }
                }

                val startDest = when {
                    PrefsHelper.getJwt(this) != null -> "profile"
                    PrefsHelper.isFirstLaunch(this) -> "welcome"
                    else -> "start"
                }

                NavHost(navController = nav, startDestination = startDest) {
                    composable("welcome") {
                        WelcomeScreen(onContinue = {
                            PrefsHelper.markLaunched(this@MainActivity)
                            nav.navigate("profile") {
                                popUpTo("welcome") { inclusive = true }
                            }
                        })
                    }
                    composable("start") {
                        StartingScreen(
                            onLogin = { startTelegramLogin() },
                            onDocsClick = { openLink("https://user-agreement.pdf") }
                        )
                    }
                    composable("profile") {
                        UsersScreen(
                            surname = "",
                            name = PrefsHelper.getFirstName(this@MainActivity) ?: "",
                            subscriptionType = "Лайт",
                            subscriptionStatus = "Активна",
                            expireDate = "30.06.2025",
                            onChangeProfile = {
                                PrefsHelper.clearAll(this@MainActivity)
                                recreate()
                            },
                            onUpgrade = { nav.navigate("subscription") },
                            onProlong = { nav.navigate("subscription") },
                            onManageSubscriptions = { nav.navigate("subscription") },
                            onSupport = { openLink("https://support.com") },
                            onDocumentation = { openLink("https://user-agreement.pdf") },
                            onTelegramBot = { openLink("https://t.me/${Config.TELEGRAM_BOT_ID}") },
                            onAboutUs = { openLink("https://ex.com/about") },
                            onDeleteAccount = {
                                PrefsHelper.clearAll(this@MainActivity)
                                recreate()
                            }
                        )
                    }
                    composable("subscription") {
                        SubscriptionChoosingScreen (
                            onPlanSelected = { plan -> nav.navigate("confirm/$plan") },
                            onBack = { nav.popBackStack() }
                        )
                    }
                    composable(
                        "confirm/{plan}",
                        arguments = listOf(navArgument("plan") { type = NavType.StringType })
                    ) { back ->
                        val plan = back.arguments!!.getString("plan")!!
                        SubscriptionConfirmationScreen(
                            subscriptionType = plan,
                            cost = when(plan) {
                                "Лайт" -> "15000 ₽"
                                "Бизнес" -> "30000 ₽"
                                "Экстра" -> "40000 ₽"
                                else -> ""
                            },
                            onEnter = { launchPayment(plan, nav) },
                            onBack  = { nav.popBackStack() }
                        )
                    }
                    composable("success") {
                        SuccessPurchaseScreen(onClose = {
                            nav.navigate("profile") {
                                popUpTo("profile") { inclusive = true }
                            }
                        })
                    }
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
         super.onNewIntent(intent)
         setIntent(intent)
         handleDeepLink(intent)
    }

    private fun handleDeepLink(intent: Intent) {
        Log.i("DEEPLINK", "got intent.data=${intent.data}")
        intent.data?.let { uri ->
            if (uri.scheme == "myapp" && uri.host == "auth-callback") {
                lifecycleScope.launch {
                    val userId = uri.getQueryParameter("id") ?: return@launch
                    val firstName = uri.getQueryParameter("first_name") ?: return@launch
                    val username = uri.getQueryParameter("username") ?: return@launch
                    val authDate = uri.getQueryParameter("auth_date") ?: return@launch
                    val hash = uri.getQueryParameter("hash") ?: return@launch

                    val authApi = NetworkModule.provideAuthApi(this@MainActivity)
                    val resp = authApi.loginWithTelegram(
                        TelegramLoginRequest(
                            id = userId,
                            first_name = firstName,
                            username = username,
                            auth_date = authDate,
                            hash = hash
                        )
                    )

                    if (resp.isSuccessful) {
                        val jwt = resp.body()!!.token
                        PrefsHelper.saveJwt(this@MainActivity, jwt)
                        PrefsHelper.saveTelegramUser(
                            this@MainActivity,
                            userId.toString(),
                            firstName,
                            username
                        )
                        fromOAuthState.value = true
                        intent.data = null
                    } else {
                        Log.e("Auth", "Login failed: ${resp.code()} ${resp.errorBody()?.string()}")
                    }
                }
            }
        }
    }

    private fun launchPayment(plan: String, nav: NavController) {
        lifecycleScope.launch {
            val jwt = PrefsHelper.getJwt(this@MainActivity)
            if (jwt.isNullOrBlank()) {
                Log.e("PAY", "JWT is null, can't create payment")
                return@launch
            }

            val userId = PrefsHelper.getTelegramId(this@MainActivity)
            if (userId.isNullOrBlank()) {
                Log.e("PAY", "Telegram ID is null")
                return@launch
            }

            val (hrs, amount) = when (plan) {
                "Лайт" -> 2 to 15_000
                "Бизнес" -> 5 to 30_000
                "Экстра" -> 8 to 40_000
                else -> {
                    Log.e("PAY", "Unknown plan: $plan")
                    return@launch
                }
            }

            val txnId = java.util.UUID.randomUUID().toString()

            val request = PaymentRequest(
                user_telegram_id = userId,
                plan = plan,
                plan_hrs = hrs,
                amount = amount,
                payment_txn_id = txnId
            )

            val api = NetworkModule.providePaymentApi(this@MainActivity)
            val resp = api.createPayment(jwt, request)

            if (resp.isSuccessful) {
                val (url, id) = resp.body()!!
                CustomTabsIntent.Builder()
                    .build()
                    .launchUrl(this@MainActivity, Uri.parse(url))
                pollUntilPaid(api::getPaymentStatus, id)
                nav.navigate("success")
            } else {
                Log.e("PAY", "Ошибка при создании платежа: ${resp.code()} ${resp.errorBody()?.string()}")
            }
        }
    }

    private fun openLink(url: String) {
        CustomTabsIntent.Builder()
            .build()
            .launchUrl(this, Uri.parse(url))
    }

    private fun startTelegramLogin() {
         val url = buildTelegramAuthUrl(
            botId = Config.TELEGRAM_BOT_ID,
            redirect = Config.TELEGRAM_REDIRECT,
            origin = Config.TELEGRAM_ORIGIN
        )
        CustomTabsIntent.Builder().build().apply {
             intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
             intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }.launchUrl(this, Uri.parse(url))
    }
}
