package com.example.rentassistantapp

import androidx.compose.ui.graphics.Color
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
import androidx.compose.foundation.layout.width
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
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.Font
import com.example.rentassistantapp.ui.theme.Red1
import com.example.rentassistantapp.ui.theme.SubscriptionChoosingScreen

@Composable
fun SubscriptionConfirmationScreen(
    modifier: Modifier = Modifier,
    subscriptionType: String,
    cost: String
) {
    Box( // box to put inner box to screen center
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box( // box storing all UI elements
            modifier = modifier
                .padding(20.dp)
                .width(340.dp)
                .height(360.dp)
                .background(color = White)
                .clip(RoundedCornerShape(16.dp))
                .border(BorderStroke(1.dp, color = Red1), shape = RoundedCornerShape(16.dp)), // todo change color of the border to White
            contentAlignment = Alignment.Center
        ) {
            Column() {
                Text(
                    text = "Всё верно? Остался один шаг",
                    fontSize = 28.sp,
                    color = Color(0xFF616163),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium,
                    modifier = modifier.fillMaxWidth().padding(top = 20.dp)
                )
                Text(
                    text = "Выбранный вами тариф:",
                    fontSize = 16.sp,
                    color = Grey2,
                    textAlign = TextAlign.Center,
                    modifier = modifier.fillMaxWidth().padding(
                        start = 80.dp,
                        end = 80.dp,
                        top = 16.dp
                    ),
                    lineHeight = 14.sp

                )
                Text(
                    text = subscriptionType,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Medium,
                    color = Red2,
                    textAlign = TextAlign.Center,
                    modifier = modifier.fillMaxWidth().padding(
                        top = 12.dp
                    )
                )
                Text(
                    text = "Стоимость:",
                    fontSize = 20.sp,
                    color = Grey2,
                    textAlign = TextAlign.Center,
                    modifier = modifier.fillMaxWidth().padding(
                        start = 60.dp,
                        end = 60.dp,
                        top = 16.dp
                    )
                )
                Text(
                    text = cost,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Medium,
                    color = Red2,
                    textAlign = TextAlign.Center,
                    modifier = modifier.fillMaxWidth().padding(
                        top = 12.dp
                    )
                )

                // Buttons
                // "Subscribe" button
                Button(
                    onClick = {/* todo перейти к оплате подписки */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                        .size(height = 40.dp, width = 318.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Red2,
                        contentColor = White
                    ),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        text = "Перейти к оплате",
                        fontSize = 20.sp
                    )
                }
                // Cancel button
                Button(
                    onClick = {/* todo отмена, возврат к тарифам */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp, end = 28.dp, top = 0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = White
                    ),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        text = "Назад",
                        fontSize = 16.sp,
                        color = Grey2,
                        textAlign = TextAlign.Center
                    )
                }

            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SubscriptionChoosingPreview(modifier: Modifier = Modifier) {
    RentAssistantAppTheme {
        SubscriptionConfirmationScreen(
            subscriptionType = "Лайт",
            cost = "30 000 ₽"
        )
    }
}