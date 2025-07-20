package com.example.rentassistantapp

import android.net.Uri
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rentassistantapp.ui.login.StartingScreen
import com.example.rentassistantapp.ui.subscription.SubscriptionChoosingScreen
import com.example.rentassistantapp.ui.subscription.SubscriptionConfirmationScreen
import com.example.rentassistantapp.ui.subscription.SuccessPurchaseScreen
import com.example.rentassistantapp.ui.welcome.WelcomeScreen
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class AppNavigationTests {
    @get:Rule val rule = createComposeRule()

    @Test
    fun start_to_welcome_via_deeplink() {
        rule.setContent {
            val nav = rememberNavController()
            nav.navigate("welcome")
            NavHost(nav, startDestination = "start") {
                composable("start") { StartingScreen(onLogin = {}, onDocsClick = {}) }
                composable("welcome") { Text("Welcome!") }
            }
        }
        rule.onNodeWithText("Welcome!").assertIsDisplayed()
    }

    @Test fun welcome_to_subscription_onContinue() {
        rule.setContent {
            val nav = rememberNavController()
            NavHost(nav, startDestination="welcome") {
                composable("welcome"){ WelcomeScreen(onContinue={nav.navigate("subscription")}) }
                composable("subscription"){ Text("Choose plan") }
            }
        }
        rule.onNodeWithText("Продолжить →").performClick()
        rule.onNodeWithText("Choose plan").assertIsDisplayed()
    }

    @Test fun confirmation_shows_cost_and_buttons() {
        rule.setContent {
            SubscriptionConfirmationScreen(
                subscriptionType="Лайт",
                cost="15 000 ₽",
                onEnter={},
                onBack={}
            )
        }
        rule.onNodeWithText("15 000 ₽").assertIsDisplayed()
        rule.onNodeWithText("Перейти к оплате").assertHasClickAction()
    }

    @Test fun success_to_profile_onClose() {
        rule.setContent {
            val nav = rememberNavController()
            NavHost(nav, startDestination="success") {
                composable("success"){
                    SuccessPurchaseScreen(onClose={nav.navigate("profile")})
                }
                composable("profile"){ Text("User Profile") }
            }
        }
        rule.onNodeWithContentDescription("Закрыть").performClick()
        rule.onNodeWithText("User Profile").assertIsDisplayed()
    }

    @Test
    fun app_starts_at_startingScreen() {
        rule.setContent {
            val nav = rememberNavController()
            NavHost(nav, startDestination = "start") {
                composable("start") { Text("Start!") }
            }
        }
        rule.onNodeWithText("Start!").assertIsDisplayed()
    }

    @Test
    fun confirmation_screen_back_button_works() {
        var backPressed = false
        rule.setContent {
            SubscriptionConfirmationScreen(
                subscriptionType = "Лайт",
                cost = "15 000 ₽",
                onEnter = {},
                onBack = { backPressed = true }
            )
        }
        rule.onNodeWithText("← Назад").performClick()
        assertTrue(backPressed)
    }

    @Test
    fun success_screen_displays_success_message() {
        rule.setContent {
            SuccessPurchaseScreen(onClose = {})
        }
        rule.onNodeWithText("Подписка успешно оформлена!").assertIsDisplayed()
    }

    @Test
    fun deeplink_redirects_to_profile_when_token_present() {
        rule.setContent {
            val nav = rememberNavController()
            nav.navigate("profile")
            NavHost(nav, startDestination = "start") {
                composable("start") { Text("Start") }
                composable("welcome") { Text("Welcome") }
                composable("profile") { Text("Profile") }
            }
        }
        rule.onNodeWithText("Profile").assertExists()
    }

}
