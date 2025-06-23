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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.Font
import com.example.rentassistantapp.ui.theme.Red1


@Composable
fun SuccessPurchaseScreen(modifier: Modifier = Modifier) {
    var closingCross: Painter = painterResource(R.drawable.vector)
    var circleWithCheckmark: Painter = painterResource(R.drawable.subtract)
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
                .border(
                    BorderStroke(1.dp, color = Red1),
                    shape = RoundedCornerShape(16.dp)
                ), // todo change color of the border to White
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = {}, // todo закрытие меню
                modifier = modifier.align(Alignment.TopEnd).padding(8.dp)
            ) {
                Icon(
                    painter = closingCross,
                    contentDescription = "Закрыть",
                    modifier = modifier.size(width = 20.dp, height = 20.dp)
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = circleWithCheckmark,
                    contentDescription = null,
                    alignment = Alignment.Center,
                    modifier = modifier.fillMaxWidth().size(width = 132.dp, height = 132.dp)
                        .padding(top = 20.dp)
                )
                Text(
                    text = "Оплата прошла успешно",
                    fontSize = 28.sp,
                    lineHeight = 28.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF616163),
                    modifier = modifier.padding(
                        start = 24.dp,
                        top = 20.dp
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Подписка активирована. Приятного использования!",
                    lineHeight = 24.sp,
                    fontSize = 20.sp,
                    color = Grey2,
                    modifier = modifier.padding(
                        start = 24.dp,
                        top = 20.dp
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SuccessPurchasePreview(modifier: Modifier = Modifier) {
    RentAssistantAppTheme {
        SuccessPurchaseScreen()
    }
}