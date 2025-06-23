package com.example.rentassistantapp.ui.welcome

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
import com.example.rentassistantapp.R
import com.example.rentassistantapp.ui.theme.Grey2
import com.example.rentassistantapp.ui.theme.RentAssistantAppTheme
import com.example.rentassistantapp.ui.theme.WhiteBase
import com.example.rentassistantapp.ui.theme.Red2
import com.example.rentassistantapp.ui.theme.Grey3
import com.example.rentassistantapp.ui.theme.White

@Composable
fun WelcomeScreen(
    onContinue: () -> Unit,
    modifier: Modifier = Modifier
) {
    val logotype = painterResource(R.drawable.logo)

    Surface(
        modifier = modifier.fillMaxSize(),
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
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Добро пожаловать!",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 32.sp,
                color = Red2
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Сервис поможет делегировать повседневные задачи — от личных до рабочих — чтобы освободить время для более важных дел.",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = Grey3
            )
        }

        Box(modifier = Modifier.fillMaxSize()) {
            Button(
                onClick = onContinue,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(52.dp)
                    .align(Alignment.BottomEnd),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Red2,
                    contentColor = White
                ),
                shape = RoundedCornerShape(50)
            ) {
                Text(text = "Продолжить →", fontSize = 20.sp)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WelcomePreview() {
    RentAssistantAppTheme {
        WelcomeScreen(onContinue = {})
    }
}
