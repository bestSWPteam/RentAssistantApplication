package com.example.rentassistantapp.ui.subscription

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.example.rentassistantapp.ui.theme.Grey2
import com.example.rentassistantapp.ui.theme.RentAssistantAppTheme
import com.example.rentassistantapp.ui.theme.WhiteBase
import com.example.rentassistantapp.ui.theme.Red2
import com.example.rentassistantapp.ui.theme.White
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.draw.clip

@Composable
fun SubscriptionChoosingScreen(
    modifier: Modifier = Modifier
) {
    Surface (
        modifier = modifier.fillMaxSize(),
        color = WhiteBase
    ){
        Column {
            Text(
                text = "Варианты подписки",
                fontSize = 28.sp,
                color = Color(0xFF616163),
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(start = 24.dp, top = 84.dp)
            )
            SubscriptionTypeSquare(modifier,
                name = "Лайт",
                shortDescription = "для себя",
                fullDescription = "Подходит для повседневных личных задач: записи к врачу, поиск подарков, бронирование билетов, напоминания, оформление заказов, помощь \n" +
                        "в планировании и мелких делах.",
                variantDescription = "pipa",
                price = "15.000")
        }
    }
}

@Composable
fun SubscriptionTypeSquare(
    modifier: Modifier = Modifier,
    name: String,
    shortDescription: String,
    fullDescription: String,
    variantDescription: String,
    price: String,
    period: String = "ежемесячно",
    onClickFunction: () -> Unit = {}

){
    Box(
        modifier = modifier
            .padding(
                start = 28.dp,
                end = 24.dp,
                top = 28.dp
            )
            .width(340.dp)
            .height(344.dp)
            .clip(RoundedCornerShape(16.dp))
            .border(BorderStroke(1.dp, color = Color(0xD97C7C7F)), shape = RoundedCornerShape(16.dp))
    ){
        Column() {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) { // for upper elements
                // column for left triangle element
                Column() {
                    Text(
                        text = name,
                        fontSize = 28.sp,
                        color = Red2,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(
                            start = 20.dp,
                            top = 24.dp
                        )
                    )
                    Text(
                        text = shortDescription,
                        fontSize = 16.sp,
                        color = Grey2,
                        modifier = Modifier.padding(
                            start = 20.dp,
                        )
                    )
                } // end of column for left triangle elements

                Column(
                    modifier = Modifier.padding(
                        top = 27.dp,
                        end = 22.dp
                    ),
                    horizontalAlignment = Alignment.End
                ) { // column for right triangle element
                    Text(
                        text = "от " + price + " ₽",
                        fontSize = 24.sp,
                        color = Red2,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = period,
                        fontSize = 16.sp,
                        color = Grey2,

                    )
                } // end of column for left triangle elements
            } // end of row for upper elements
            Text(
                text = fullDescription
            )
            Text(
                text = variantDescription
            )

            // "Subscribe" button
            Button(
                onClick = {onClickFunction},
                modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp, horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Red2,
                    contentColor = White
                ),
                shape = RoundedCornerShape(50)
            ) {
                Text(text = "Оформить подписку",
                    fontSize = 16.sp)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SubscriptionChoosingPreview(modifier: Modifier = Modifier) {
    RentAssistantAppTheme {
        SubscriptionChoosingScreen(

        )
    }
}