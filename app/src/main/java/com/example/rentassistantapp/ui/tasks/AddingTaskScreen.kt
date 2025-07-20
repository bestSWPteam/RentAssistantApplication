package com.example.rentassistantapp.ui.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rentassistantapp.ui.theme.Red2
import com.example.rentassistantapp.ui.theme.RentAssistantAppTheme
import com.example.rentassistantapp.ui.theme.White
import com.example.rentassistantapp.ui.theme.WhiteBase

@Composable
fun AddTaskScreen(
    onTaskCreated: (String, String) -> Unit,
    onBack: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val isButtonEnabled = title.isNotBlank() && description.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WhiteBase)
            .padding(16.dp)
    ) {
        // Screen title
        Text(
            text = "Добавление задачи",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Red2,
            modifier = Modifier.padding(bottom = 24.dp, top = 24.dp)
        )

        // Task title
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Краткое описание задачи") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Task description
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            label = { Text("Полное описание задачи") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Button to create a task
        Button(
            onClick = {
                onTaskCreated(title, description)
                onBack()
            },
            enabled = isButtonEnabled,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Red2,
                contentColor = White,
                disabledContainerColor = Red2.copy(alpha = 0.5f),
                disabledContentColor = White.copy(alpha = 0.7f)
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = "Оставить ассистенту",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AddTaskScreenPreview() {
    RentAssistantAppTheme {
        AddTaskScreen(
            onTaskCreated = {arg1, arg2 -> {}},
            onBack = {}
        )
    }
}