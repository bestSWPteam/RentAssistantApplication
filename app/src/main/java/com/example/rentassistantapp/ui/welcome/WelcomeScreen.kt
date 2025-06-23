package com.example.rentassistantapp.ui.welcome

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rentassistantapp.ui.theme.*

@Composable
fun WelcomeScreen(
    onContinue: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier.fillMaxSize(), color = WhiteBase) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize().padding(24.dp)
        ) {
            Text(
                text = "Добро пожаловать!",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Red2,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Сервис поможет делегировать повседневные задачи…",
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                color = Grey3
            )
            Spacer(modifier = Modifier.height(48.dp))
            Button(
                onClick = onContinue,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Red2,
                    contentColor = White
                ),
                shape = RoundedCornerShape(50),
                modifier = Modifier.fillMaxWidth().height(48.dp)
            ) {
                Text(text = "Продолжить →", fontSize = 18.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomePreview() {
    RentAssistantAppTheme {
        WelcomeScreen(onContinue = {})
    }
}