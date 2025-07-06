package com.example.rentassistantapp.ui.subscription

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
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
import com.example.rentassistantapp.ui.theme.Grey2
import com.example.rentassistantapp.ui.theme.Grey3
import com.example.rentassistantapp.ui.theme.Red2
import com.example.rentassistantapp.ui.theme.RentAssistantAppTheme
import com.example.rentassistantapp.ui.theme.White
import com.example.rentassistantapp.ui.theme.WhiteBase

@Composable
fun SubscriptionChoosingScreen(
    onPlanSelected: (planType: String, hours: Int) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier.Companion = Modifier
) {
    val scrollState = rememberScrollState()

    Surface(
        modifier = modifier
            .fillMaxSize()
            .background(WhiteBase),
        color = WhiteBase
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(bottom = 32.dp)
        ) {
            Text(
                text = "← Назад",
                fontSize = 16.sp,
                color = Red2,
                modifier = Modifier
                    .padding(start = 24.dp, top = 32.dp)
                    .clickable { onBack() }
            )
            Text(
                text = "Варианты подписки",
                fontSize = 28.sp,
                color = Grey3,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 24.dp, top = 84.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            listOf(
                Triple("Лайт", "для себя", listOf(
                    2 to "15 000 ₽",
                    5 to "30 000 ₽",
                    8 to "50 000 ₽"
                )),
                Triple("Бизнес", "для бизнеса", listOf(
                    2 to "30 000 ₽",
                    5 to "60 000 ₽",
                    8 to "80 000 ₽"
                )),
                Triple("Экстра", "максимум пользы", listOf(
                    2 to "40 000 ₽",
                    5 to "80 000 ₽",
                    8 to "100 000 ₽"
                ))
            ).forEach { (name, shortDesc, options) ->
                options.forEach { (hours, price) ->
                    SubscriptionTypeCard(
                        name = name,
                        shortDescription = shortDesc,
                        price = price,
                        hours = hours,
                        onClick = { onPlanSelected(name, hours) }
                    )
                }
            }
        }
    }
}

@Composable
fun SubscriptionTypeCard(
    name: String,
    shortDescription: String,
    price: String,
    hours: Int,
    period: String = "ежемесячно",
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 12.dp)
            .fillMaxWidth()
            .height(220.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Grey2)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = name,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Red2
                    )
                    Text(
                        text = shortDescription,
                        fontSize = 16.sp,
                        color = Grey2
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "$price",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Red2
                    )
                    Text(
                        text = "$hours ч/день",
                        fontSize = 14.sp,
                        color = Grey2
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = getFullDescription(name),
                fontSize = 14.sp,
                color = Grey3,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Red2,
                    contentColor = White
                ),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text(text = "Оформить подписку", fontSize = 16.sp)
            }
        }
    }
}

private fun getFullDescription(planName: String): String {
    return when (planName) {
        "Лайт" -> "Подходит для повседневных личных задач: записи к врачу, поиск подарков, бронирование билетов, напоминания, оформление заказов, помощь в планировании и мелких делах."
        "Бизнес" -> "Подходит для бизнес-коммуникаций, аналитических запросов, организации встреч, ведения переписки, составления документов и других рабочих задач."
        "Экстра" -> "Подходит для комплексной поддержки: бизнес-коммуникации, организация встреч, аналитика, а также личные дела — записи, напоминания, бронирования, заказы и повседневная рутина."
        else -> ""
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SubscriptionChoosingPreview() {
    RentAssistantAppTheme {
        SubscriptionChoosingScreen(onPlanSelected = { _, _ -> }, onBack = {})
    }
}
