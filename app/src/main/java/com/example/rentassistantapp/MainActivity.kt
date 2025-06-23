package com.example.rentassistantapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.browser.customtabs.CustomTabsIntent
import com.example.rentassistantapp.ui.login.StartingScreen
import com.example.rentassistantapp.ui.welcome.WelcomeScreen
import com.example.rentassistantapp.ui.theme.RentAssistantAppTheme
import com.example.rentassistantapp.util.Config
import android.util.Log
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
private fun RedirectHandler(
    nav: NavHostController,
    incomingUri: Uri?
) {
    var handled by remember { mutableStateOf(false) }

    LaunchedEffect(incomingUri) {
        if (incomingUri != null && !handled) {
            Log.d("MAIN", "RedirectHandler fired, URI = $incomingUri")
            handled = true
            nav.navigate("welcome")
        }
    }
}

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
                        StartingScreen(onLogin = {
                            val url = Uri.Builder()
                                .scheme("https")
                                .authority("oauth.telegram.org")
                                .appendPath("auth")
                                .appendQueryParameter("bot_id", botId)
                                .appendQueryParameter("origin", origin)
                                .appendQueryParameter("redirect_url", redirectUri)
                                .appendQueryParameter("request_access", "write")
                                .build()
                                .toString()
                            CustomTabsIntent.Builder().build()
                                .launchUrl(this@MainActivity, Uri.parse(url))
                        })
                    }
                    composable("welcome") {
                        WelcomeScreen(onContinue = {
                            // TODO: navigate to main app screen
                        })
                    }
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
    }
}


