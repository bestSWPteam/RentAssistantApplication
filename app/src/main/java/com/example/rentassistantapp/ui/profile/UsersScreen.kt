package com.example.rentassistantapp.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rentassistantapp.ui.theme.Grey1
import com.example.rentassistantapp.ui.theme.RentAssistantAppTheme
import com.example.rentassistantapp.ui.theme.WhiteBase
import com.example.rentassistantapp.ui.theme.Red2
import com.example.rentassistantapp.ui.theme.Grey3
import com.example.rentassistantapp.ui.theme.White
import com.example.rentassistantapp.R

@Composable
fun UsersScreen(modifier: Modifier = Modifier) {
    var avatar: Painter = painterResource(R.drawable.avatar_plug)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().background(color = Grey1)
            .padding(top = 68.dp)
    ) {
        Image(  // avatar
            painter = avatar,
            contentDescription = "Users avatar",
            alignment = Alignment.Center,
            modifier = Modifier.size(height = 104.dp, width = 104.dp)
        )
        Text(  // name
            text = "Агафонова Арина Кирилловна"
        )
        Text(
            text = "Привязан аккаунт Telegram"
        )
        Text(  // todo кнопка изменить
            text = "Изменить"
        )
        Text(
            text = "Мои подписки"
        )
        Text(
            text = "Активна"
        )
        Box(){} // create box of the subscription
        Row (){  // todo заменить на кнопки
            Text(
                text = "Улучшить"
            )
            Text(
                text = "Продлить"
            )
        }
        Text(
            text = "Управление подписками"
        )
        Text(
            text = "Поддержка"
        )
        Text(
            text = "Документация"
        )
        Text(
            text = "Telegram Бот"
        )
        Text(
            text = "О нас"
        )
        Text(
            text = "Удалить аккаунт"
        )
        Box () {} // create box for bottom panel
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UsersScreenPreview(modifier: Modifier = Modifier) {
    RentAssistantAppTheme {
        UsersScreen()
    }
}