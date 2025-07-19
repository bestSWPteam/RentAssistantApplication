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
import com.example.rentassistantapp.ui.theme.Grey3
import com.example.rentassistantapp.ui.theme.RentAssistantAppTheme
import com.example.rentassistantapp.ui.theme.WhiteBase
import com.example.rentassistantapp.ui.theme.Red2
import com.example.rentassistantapp.ui.theme.White
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.draw.clip

@Composable
fun TariffChoosingScreen(
    modifier: Modifier = Modifier,
    selectedPlan: String?,
    prices: Array<String>,
    onPlanSelected: () -> Unit
) {
    val scrollState = rememberScrollState()

    Surface (
        modifier = modifier.fillMaxSize(),
        color = WhiteBase
    ){
        Column (
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(bottom = 32.dp)
        ){
            Text(
                text = "Выбрано: ${selectedPlan ?: "—"}",
                fontSize = 28.sp,
                color = Color(0xFF616163),
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(start = 24.dp, top = 84.dp)
            )

            // Lite subscription
            TariffTypeSquare(modifier,
                name = "Лайт",
                fullDescription = "Идеален для краткосрочных задач. Подходит для быстрого " +
                        "выполнения небольших проектов и эффективного использования времени.",
                price = prices[0],
                onClickFunction = onPlanSelected
            )

            // Business subscription
            TariffTypeSquare(modifier,
                name = "Бизнес",
                fullDescription = "Оптимален для средних задач. Обеспечивает баланс между временем " +
                        "и стоимостью, позволяя эффективно решать задачи.",
                price = prices[1],
                onClickFunction = onPlanSelected
            )

            // Extra subscription
            TariffTypeSquare(modifier,
                name = "Экстра",
                fullDescription = "Подходит для крупных проектов. Обеспечивает максимальную " +
                        "продуктивность и глубокое погружение в работу для достижения результатов.",
                price = prices[2],
                onClickFunction = onPlanSelected
            )
        }
    }
}

@Composable
fun TariffTypeSquare(
    modifier: Modifier = Modifier,
    name: String,
    fullDescription: String,
    price: String,
    period: String = "в день",
    onClickFunction: () -> Unit

){
    Box(
        modifier = modifier
            .padding(
                start = 28.dp,
                end = 24.dp,
                top = 28.dp
            )
            .width(340.dp)
            .height(284.dp)
            .clip(RoundedCornerShape(16.dp))
            .border(
                BorderStroke(1.dp, color = Color(0xD97C7C7F)),
                shape = RoundedCornerShape(16.dp)
            )
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
                        fontSize = 36.sp,
                        color = Red2,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(
                            start = 20.dp,
                            top = 24.dp
                        )
                    )
                } // end of column for left triangle elements

                Column(
                    modifier = Modifier.padding(
                        top = 28.dp,
                        end = 22.dp
                    ),
                    horizontalAlignment = Alignment.End
                ) { // column for right triangle element
                    Text(
                        text = period,
                        fontSize = 24.sp,
                        color = Grey2,

                        )
                } // end of column for left triangle elements
            } // end of row for upper elements


            Column (modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally){
                Text(
                    text = fullDescription,
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    color = Grey3,
                    modifier = Modifier.padding(
                        start = 20.dp,
                        top = 8.dp
                    )
                )
                Text(
                    text = price,
                    fontSize = 36.sp,
                    color = Grey3,
                    modifier = Modifier.padding(
                        start = 20.dp,
                        top = 20.dp
                    ),
                    textAlign = TextAlign.Center
                )
            }
            // "Subscribe" button
            Button(
                onClick = onClickFunction,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 16.dp),
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
fun TariffChoosingPreview() {
    RentAssistantAppTheme {
        TariffChoosingScreen(
            onPlanSelected = {},
            prices = arrayOf("15 000₽", "30 000₽", "50 000₽"),
            modifier = Modifier,
            selectedPlan = "Лайт"
        )
    }
}