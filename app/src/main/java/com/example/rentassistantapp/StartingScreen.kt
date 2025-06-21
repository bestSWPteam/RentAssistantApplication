package com.example.rentassistantapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rentassistantapp.ui.theme.Grey2
import com.example.rentassistantapp.ui.theme.RentAssistantAppTheme
import com.example.rentassistantapp.ui.theme.WhiteBase
import com.example.rentassistantapp.ui.theme.Red2
import com.example.rentassistantapp.ui.theme.Grey3
import com.example.rentassistantapp.ui.theme.White

@Composable
fun StartingScreen(modifier: Modifier = Modifier) {
    var logotype = painterResource(R.drawable.logo)

    Surface(
        modifier = modifier.fillMaxSize(),
        color = WhiteBase) {

        // logotype
        Image(
            painter = logotype,
            contentDescription = "Logo \"Rent Assistant\"",
            modifier = Modifier.size(20.dp).padding(vertical = 80.dp, horizontal = 100.dp),
            alignment = Alignment.TopStart
        )
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Войдите в свой аккаунт с помощью Telegram",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = Grey3,
                modifier = Modifier.padding(horizontal = 72.dp)
            )

            // "Enter" button
            Button(
                onClick = { /* TODO вызов авторизации через телегграм на начальном экране*/ },
                modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp, horizontal = 52.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Red2,
                    contentColor = White
                ),
                shape = RoundedCornerShape(50)
            ) {
                Text(text = "Войти",
                    fontSize = 20.sp)
            }
        }

        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(vertical = 40.dp)
        ) {
            Text(
                text = "Документация",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = Grey2,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )

            Text(
                text = "Что-то пошло не так? Напишите нам",
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                color = Grey2
            )
        }
    }

} // Add clickability of Documentation, write us






@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StartingPreview(modifier: Modifier = Modifier) {
    RentAssistantAppTheme {
        StartingScreen()
    }
}