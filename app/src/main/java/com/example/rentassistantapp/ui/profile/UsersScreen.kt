package com.example.rentassistantapp.ui.profile

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rentassistantapp.R
import com.example.rentassistantapp.ui.theme.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.example.rentassistantapp.ui.navigationBackend.BottomNavigationBar

@Composable
fun UsersScreen(
    surname: String,
    name: String,
    subscriptionType: String,
    expireDate: String,
    subscriptionStatus: String,
    onChangeProfile: () -> Unit,
    onUpgrade: () -> Unit,
    onProlong: () -> Unit,
    onManageSubscriptions: () -> Unit,
    onSupport: () -> Unit,
    onDocumentation: () -> Unit,
    onTelegramBot: () -> Unit,
    onAboutUs: () -> Unit,
    onDeleteAccount: () -> Unit,
    onTasksExamples: () -> Unit,
    navController: NavController
) {
    val avatar: Painter = painterResource(R.drawable.avatar_plug)
    Box{
        Scaffold(bottomBar = { BottomNavigationBar(navController)} ) { innerPadding ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(WhiteBase)
                    .padding(top = 36.dp, bottom = 12.dp)
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
            ) {
                Image(
                    painter = avatar,
                    contentDescription = "User avatar",
                    modifier = Modifier.size(104.dp)
                )

                Text(
                    text = surname,
                    fontSize = 24.sp,
                    color = Grey3,
                    modifier = Modifier.padding(top = 12.dp)
                )
                Text(
                    text = name,
                    fontSize = 24.sp,
                    color = Grey3,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Text(
                    text = "Привязан аккаунт Telegram",
                    fontSize = 16.sp,
                    color = Grey2,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Text(
                    text = "Изменить",
                    fontSize = 16.sp,
                    color = Grey2,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable(onClick = onChangeProfile)
                )

                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(start = 24.dp, end = 26.dp, top = 28.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Мои подписки",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium,
                        color = Grey3
                    )
                    Text(text = subscriptionStatus, fontSize = 16.sp, color = Red2)
                }

                BoxWithSubscriptionInfo(
                    subscriptionType = subscriptionType,
                    expireDate = expireDate
                )

                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, top = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = onUpgrade,
                        modifier = Modifier.height(36.dp).width(160.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Red2,
                            contentColor = White
                        ),
                        shape = RoundedCornerShape(24.dp)
                    ) {
                        Text(text = "Улучшить", fontSize = 18.sp, fontWeight = FontWeight.Medium)
                    }
                    Button(
                        onClick = onProlong,
                        modifier = Modifier.height(36.dp).width(160.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Red2,
                            contentColor = White
                        ),
                        shape = RoundedCornerShape(24.dp)
                    ) {
                        Text(text = "Продлить", fontSize = 18.sp, fontWeight = FontWeight.Medium)
                    }
                }

                ButtonWithArrow("Управление подписками", onClick = onManageSubscriptions)
                ButtonWithArrow("Поддержка", onClick = onSupport)
                ButtonWithArrow("Примеры задач", onClick = onTasksExamples)
                ButtonWithArrow("Документация", onClick = onDocumentation)
                ButtonWithArrow("Telegram Бот", onClick = onTelegramBot)
                ButtonWithArrow("О нас", onClick = onAboutUs)

                Button(
                    onClick = onDeleteAccount,
                    modifier = Modifier.height(36.dp).width(200.dp)
                        .border(BorderStroke(1.dp, Grey2), shape = RoundedCornerShape(24.dp)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = WhiteBase,
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
}

@Composable
fun BoxWithSubscriptionInfo(subscriptionType: String, expireDate: String) {
    Box(
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp, top = 14.dp)
            .fillMaxWidth()
            .height(156.dp)
            .clip(RoundedCornerShape(16.dp))
            .border(BorderStroke(1.dp, Grey2), shape = RoundedCornerShape(16.dp))
            .background(White)
    ) {
        Column(modifier = Modifier.padding(start = 18.dp)) {
            Text("Сейчас активен тариф:", fontSize = 16.sp, color = Grey2, modifier = Modifier.padding(top = 16.dp))
            Text(subscriptionType, fontSize = 24.sp, color = Red2, fontWeight = FontWeight.Medium, modifier = Modifier.padding(top = 2.dp))
            Text("Подписка активна до:", fontSize = 16.sp, color = Grey2, modifier = Modifier.padding(top = 10.dp))
            Text(expireDate, fontSize = 24.sp, color = Red2, fontWeight = FontWeight.Medium, modifier = Modifier.padding(top = 6.dp))
        }
    }
}

@Composable
fun ButtonWithArrow(text: String, onClick: () -> Unit) {
    val arrow: Painter = painterResource(R.drawable.arrow_right)
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 4.dp),
        colors = ButtonDefaults.buttonColors(containerColor = WhiteBase, contentColor = Grey3)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = text, fontSize = 20.sp, fontWeight = FontWeight.Normal)
            Image(painter = arrow, contentDescription = null, modifier = Modifier.size(24.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UsersScreenPreview() {
    RentAssistantAppTheme {
        UsersScreen(
            surname = "Гиззатуллин",
            name = "Камиль",
            subscriptionType = "Лайт",
            expireDate = "30.06.2025",
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
            onTasksExamples = {},
            navController = rememberNavController()
        )
    }
}
