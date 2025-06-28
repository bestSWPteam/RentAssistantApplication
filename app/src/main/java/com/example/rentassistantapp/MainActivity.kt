package com.example.rentassistantapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.rentassistantapp.ui.login.StartingScreen
import com.example.rentassistantapp.ui.welcome.WelcomeScreen
import com.example.rentassistantapp.ui.subscription.SubscriptionChoosingScreen
import com.example.rentassistantapp.ui.subscription.SubscriptionConfirmationScreen
import com.example.rentassistantapp.ui.subscription.SuccessPurchaseScreen
import com.example.rentassistantapp.ui.profile.UsersScreen
import com.example.rentassistantapp.ui.tasks.TasksScreen
import com.example.rentassistantapp.ui.theme.RentAssistantAppTheme
import com.example.rentassistantapp.util.Config

class MainActivity : ComponentActivity() {
    private val botId = Config.TELEGRAM_BOT_ID
    private val origin = Config.TELEGRAM_ORIGIN
    private val redirectUri = Config.TELEGRAM_REDIRECT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RentAssistantAppTheme {
                val nav = rememberNavController()
                RedirectHandler(nav, intent.data)

                NavHost(navController = nav, startDestination = "start") {
                    composable("start") {
                        StartingScreen(
                            onLogin = { startTelegramAuth() },
                            onDocsClick = { openDocs() }
                        )
                    }

                    composable("welcome") {
                        WelcomeScreen(onContinue = {
                            nav.navigate("subscription")
                        })
                    }

                    composable("subscription") {
                        SubscriptionChoosingScreen(
                            onPlanSelected = { planType ->
                                nav.navigate("confirm/$planType")
                            }
                        )
                    }

                    composable(
                        "confirm/{planType}",
                        arguments = listOf(navArgument("planType") {
                            type = NavType.StringType
                        })
                    ) { backStackEntry ->
                        val plan = backStackEntry.arguments!!.getString("planType")!!
                        SubscriptionConfirmationScreen(
                            subscriptionType = plan,
                            cost = when(plan) {
                                "Лайт" -> "15 000 ₽"
                                "Бизнес" -> "30 000 ₽"
                                "Экстра" -> "40 000 ₽"
                                else -> "—"
                            },
                            onEnter = {
                                // TODO: вызвать API payments/create и получить платежную ссылку
                                nav.navigate("success")
                            },
                            onBack = { nav.popBackStack() }
                        )
                    }

                    composable("success") {
                        SuccessPurchaseScreen(onClose = {
                            nav.navigate("profile") {
                                popUpTo("start") { inclusive = false }
                            }
                        })
                    }

                    composable("profile") {
                        UsersScreen(
                            surname = "Гиззатуллин",
                            name = "Камиль",
                            subscriptionType = "Лайт", // TODO: взять из prefs или API
                            subscriptionStatus = "Активна", // TODO
                            expireDate = "27.07.25", // TODO
                            onChangeProfile = { /* TODO */ },
                            onUpgrade = { nav.navigate("subscription") },
                            onProlong = { /* TODO */ },
                            onManageSubscriptions = { /* TODO */ },
                            onSupport = { /* TODO: чат или почта */ },
                            onDocumentation = { openDocs() },
                            onTelegramBot = { startTelegramAuth() },
                            onAboutUs = { /* TODO */ },
                            onDeleteAccount = { /* TODO */ }
                        )
                    }

                    composable("tasks") {
                        TasksScreen(
                            isSubscription = false,
                            onGoToSubscription = { /* TODO: переход на подписку */ },
                            onFilter = {
                                // TODO: открыть фильтр задач
                            }
                        ))
                    }
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
    }

    private fun startTelegramAuth() {
        val url = Uri.Builder()
            .scheme("https")
            .authority("oauth.telegram.org")
            .appendPath("auth")
            .appendQueryParameter("bot_id", botId)
            .appendQueryParameter("redirect_url", redirectUri)
            .appendQueryParameter("request_access", "write")
            .build()
            .toString()
        CustomTabsIntent.Builder().build().launchUrl(this, Uri.parse(url))
    }

    private fun openDocs() {
        val docsUrl = "https://www.consultant.ru/document/cons_doc_LAW_61801/"
        CustomTabsIntent.Builder().build().launchUrl(this, Uri.parse(docsUrl))
    }
}

@Composable
private fun RedirectHandler(nav: NavHostController, incomingUri: Uri?) {
    var handled by remember { mutableStateOf(false) }
    LaunchedEffect(incomingUri) {
        if (incomingUri != null && !handled) {
            handled = true
            nav.navigate("welcome")
        }
    }
}
