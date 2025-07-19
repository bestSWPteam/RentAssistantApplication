package com.example.rentassistantapp.ui.subscription

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SubscriptionHoursScreen(
    plan: String,
    onHoursSelected: (Int) -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Тариф: $plan", fontSize = 24.sp, modifier = Modifier.padding(16.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { onHoursSelected(2) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("2 часа")
        }
        Button(
            onClick = { onHoursSelected(5) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("5 часов")
        }
        Button(
            onClick = { onHoursSelected(8) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("8 часов")
        }
        Spacer(modifier = Modifier.weight(1f))
        TextButton(onClick = onBack) {
            Text("Назад")
        }
    }
}
