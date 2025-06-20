package com.example.rentassistantapp

import androidx.compose.foundation.Image
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.rentassistantapp.ui.theme.RentAssistantAppTheme

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier) {
    var logotype = painterResource(R.drawable.logo)

    Image(
        painter = logotype,
        contentDescription = "Logo \"Rent Assistant\""
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RentAssistantAppTheme {
        WelcomeScreen()
    }
}
