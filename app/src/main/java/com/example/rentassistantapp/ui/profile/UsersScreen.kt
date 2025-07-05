package com.example.rentassistantapp.ui.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rentassistantapp.ui.theme.RentAssistantAppTheme
import com.example.rentassistantapp.ui.theme.*
import com.example.rentassistantapp.R
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.*
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


import com.example.rentassistantapp.ui.navigationBackend.BottomNavigationBar


@Composable
fun UsersScreen(
    modifier: Modifier = Modifier,
    surname: String,
    name: String,
    subscriptionType: String,
    subscriptionStatus: String,
    expireDate: String,
    // functions
    onChangeProfile: () -> Unit,
    onUpgrade: () -> Unit,
    onProlong: () -> Unit,
    onManageSubscriptions: () -> Unit,
    onSupport: () -> Unit,
    onDocumentation: () -> Unit,
    onTelegramBot: () -> Unit,
    onAboutUs: () -> Unit,
    onDeleteAccount: () -> Unit,
    // navigator
    navController: NavController

    ) {
    var avatar: Painter = painterResource(R.drawable.avatar_plug)
    var scrollState = rememberScrollState()
    Scaffold(
        bottomBar = {BottomNavigationBar(navController)}
    ) {
        innerPadding ->


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = WhiteBase)
                .padding(innerPadding)
                .padding(top = 16.dp)
                .verticalScroll(scrollState)
        ) {
            Image(  // avatar
                painter = avatar,
                contentDescription = "Users avatar",
                alignment = Alignment.Center,
                modifier = Modifier.size(height = 104.dp, width = 104.dp)
            )
            Text(  // surname
                text = surname,
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                color = Grey3,
                modifier = modifier.padding(top = 12.dp)
            )
            Text(  // name
                text = name,
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                color = Grey3,
                modifier = modifier.padding(top = 8.dp)
            )
            Text(
                text = "Привязан аккаунт Telegram",
                fontSize = 16.sp,
                color = Grey2,
                modifier = modifier.padding(top = 8.dp)
            )
            Text(
                text = "Изменить ",
                fontSize = 16.sp,
                color = Grey2,
                fontWeight = FontWeight.Bold,
                modifier = modifier.clickable(
                    onClick = onChangeProfile
                )
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        start = 24.dp,
                        end = 26.dp,
                        top = 28.dp
                    )
            ) {
                Text(
                    text = "Мои подписки",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF616163),
                )
                Text(
                    text = subscriptionStatus,
                    fontSize = 16.sp,
                    color = Red2
                )
            }

            BoxWithSubscriptionInfo(
                subscriptionType = subscriptionType,
                expireDate = expireDate
            )

            Row(
                modifier = modifier.fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 28.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(  // Red button upgrade subscription
                    onClick = onUpgrade,
                    modifier = Modifier
                        .height(36.dp)
                        .width(152.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Red2,
                        contentColor = White
                    ),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Text(text = "Улучшить", fontSize = 18.sp, fontWeight = FontWeight.Medium)
                }
                Button(  // Red button prolong subscription
                    onClick = onProlong,
                    modifier = Modifier
                        .height(36.dp)
                        .width(152.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Red2,
                        contentColor = White
                    ),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Text(text = "Продлить", fontSize = 18.sp, fontWeight = FontWeight.Medium)
                }
            }
            // bottom buttons (not panel)
            ButtonWithArrow(
                text = "Управление подписками",
                modifier = Modifier.padding(top = 20.dp),
                onClick = onManageSubscriptions
            )
            ButtonWithArrow(
                text = "Поддержка",
                modifier = Modifier.padding(top = 0.dp),
                onClick = onSupport
            )
            ButtonWithArrow(
                text = "Документация",
                modifier = Modifier.padding(top = 0.dp),
                onClick = onDocumentation
            )
            ButtonWithArrow(
                text = "Telegram Бот",
                modifier = Modifier.padding(top = 0.dp),
                onClick = onTelegramBot
            )
            ButtonWithArrow(
                text = "О нас",
                modifier = Modifier.padding(top = 0.dp),
                onClick = onAboutUs
            )

            Button(  // Button delete the account
                onClick = onDeleteAccount,
                modifier = Modifier
                    .height(36.dp)
                    .width(200.dp)
                    .border(BorderStroke(1.dp, Grey2), shape = RoundedCornerShape(24.dp))
                    .padding(end = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = WhiteBase, // todo make border thinker
                    contentColor = Grey2
                ),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text(text = "Удалить аккаунт", fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun BoxWithSubscriptionInfo(
    modifier: Modifier = Modifier,
    subscriptionType: String,
    expireDate: String
){
    Box(
        modifier = Modifier
            .padding(
                start = 24.dp, end = 24.dp, top = 14.dp
            )
            .fillMaxWidth()
            .height(156.dp)
            .background(color = WhiteBase)
            .clip(RoundedCornerShape(16.dp))
            .border(BorderStroke(1.dp, Grey2), shape = RoundedCornerShape(16.dp))
    ) {
        Column(
            modifier = modifier.padding(start = 18.dp)
        ){
            Text(
                text = "Сейчас активен тариф:",
                fontSize = 16.sp,
                color = Grey2,
                modifier = modifier.padding(top = 16.dp)
            )
            Text(
                text = subscriptionType,
                fontSize = 24.sp,
                color = Red2,
                fontWeight = FontWeight.Medium,
                modifier = modifier.padding(top = 2.dp)
            )
            Text(
                text = "Подписка активна до:",
                fontSize = 16.sp,
                color = Grey2,
                modifier = modifier.padding(top = 10.dp)
            )
            Text(
                text = expireDate,
                fontSize = 24.sp,
                color = Red2,
                fontWeight = FontWeight.Medium,
                modifier = modifier.padding(top = 6.dp)
            )
        }
    }
}

@Composable
fun ButtonWithArrow( // very sufferable thing. Change with extremal cautious
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var arrow: Painter = painterResource(R.drawable.arrow_right)
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = WhiteBase,
            contentColor = Grey3
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = text,
                fontSize = 20.sp,
                color = Grey3,
                fontWeight = FontWeight.Normal
            )
            Image(
                painter = arrow,
                contentDescription = null,
                modifier = Modifier.size(24.dp, 24.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UsersScreenPreview() {
    RentAssistantAppTheme {
        val nav = rememberNavController()
        UsersScreen(
            surname = "Агафонова",
            name = "Арина Кирилловна",
            subscriptionType = "Лайт",
            expireDate = "07.07.25",
            subscriptionStatus = "Активна",
            onChangeProfile = {},
            onUpgrade = {},
            onProlong = {},
            onManageSubscriptions = {},
            onSupport = {},
            onDocumentation = {},
            onTelegramBot = {},
            onAboutUs = {},
            onDeleteAccount = {},
            navController = nav
        )
    }
}