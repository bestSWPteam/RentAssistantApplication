package com.example.rentassistantapp.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.rentassistantapp.R
import com.example.rentassistantapp.ui.theme.*

@Composable
fun StartingScreen(
    onLogin: () -> Unit,
    onDocsClick: () -> Unit,
    onSupportClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val logotype = painterResource(R.drawable.logo)

    Surface(
        modifier = modifier
            .fillMaxSize(),
        color = WhiteBase
    ) {
        Image(
            painter = logotype,
            contentDescription = "Logo \"Rent Assistant\"",
            modifier = Modifier
                .size(20.dp)
                .padding(vertical = 80.dp, horizontal = 100.dp),
            alignment = Alignment.TopStart
        )

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Войдите в свой аккаунт с помощью Telegram",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = Grey3,
                modifier = Modifier.padding(horizontal = 72.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onLogin,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 52.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Red2,
                    contentColor = White
                ),
                shape = RoundedCornerShape(50)
            ) {
                Text(text = "Войти", fontSize = 20.sp)
            }
        }

        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().padding(bottom = 40.dp)
        ) {
            Text(
                text = "Документация",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = Grey2,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable(onClick = onDocsClick).padding(8.dp)
            )
            Text(
                text = "Что-то пошло не так? Напишите нам",
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                color = Grey2,
                modifier = Modifier.clickable(onClick = onSupportClick)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StartingPreview() {
    RentAssistantAppTheme {
        StartingScreen(onLogin = {}, onDocsClick = {},
            onSupportClick = {})
    }
}
