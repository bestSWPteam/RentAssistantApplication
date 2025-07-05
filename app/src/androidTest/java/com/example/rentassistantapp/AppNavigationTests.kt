package com.example.rentassistantapp

import android.net.Uri
import androidx.compose.material3.Text
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
import com.example.rentassistantapp.util.RedirectHandler
import org.junit.Rule
import org.junit.Test

class AppNavigationTests {
    @get:Rule val rule = createComposeRule()

    @Test fun start_to_welcome_via_deeplink() {
        rule.setContent {
            val nav = rememberNavController()
            RedirectHandler(nav, Uri.parse("myapp://auth-callback?test=1"))
            NavHost(nav, startDestination="start") {
                composable("start"){ StartingScreen(onLogin={}, onDocsClick={}) }
                composable("welcome"){ Text("Welcome!") }
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

    @Test fun subscription_to_confirmation_onPlanClick() {
        rule.setContent {
            val nav = rememberNavController()
            NavHost(nav, startDestination="subscription") {
                composable("subscription"){
                    SubscriptionChoosingScreen(onPlanSelected ={nav.navigate("confirm/$it")})
                }
                composable("confirm/{planType}"){
                    Text("Confirm screen")
                }
            }
        }
        rule.onNodeWithText("Лайт").performClick()
        rule.onNodeWithText("Confirm screen").assertIsDisplayed()
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
}
