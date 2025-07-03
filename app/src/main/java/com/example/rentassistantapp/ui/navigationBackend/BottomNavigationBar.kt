package com.example.rentassistantapp.ui.navigationBackend

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rentassistantapp.ui.theme.Grey1
import com.example.rentassistantapp.ui.theme.Grey3

import com.example.rentassistantapp.ui.theme.WhiteBase

@Composable
fun BottomNavigationBar(navController: NavController) {
    val currentRoute = navController.currentBackStackEntry?.destination?.route

    BottomAppBar(
        containerColor = WhiteBase,
        modifier = Modifier // Добавляем верхнюю границу
            .border(
                width = 1.dp,
                color = Grey3, // Ваш цвет разделительной линии
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Profile button
            NavigationButton(
                icon = Icons.Filled.Person,
                label = "Профиль",
                isSelected = currentRoute == "profile",
                onClick = {
                    if (currentRoute != "profile") {
                        navController.navigate("profile") {
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                modifier = Modifier.weight(1f),
                iconSize = 22.dp
            )

            // Tasks button
            NavigationButton(
                icon = Icons.Filled.Check,
                label = "Задачи",
                isSelected = currentRoute == "tasks",
                onClick = {
                    if (currentRoute != "tasks") {
                        navController.navigate("tasks") {
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                modifier = Modifier.weight(1f),
                iconSize = 22.dp
            )
        }
    }
}

@Composable
fun NavigationButton(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconSize: Dp = 24.dp
) {
    val tintColor = if (isSelected) Color.Red else Grey3

    Column(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            modifier = Modifier.size(iconSize),
            tint = tintColor
        )
        Text(
            text = label,
            fontSize = 12.sp,
            color = tintColor
        )
    }
}