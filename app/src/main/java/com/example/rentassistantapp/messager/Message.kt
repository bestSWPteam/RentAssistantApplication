package com.example.rentassistantapp.messager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rentassistantapp.ui.theme.BrightPink
import com.example.rentassistantapp.ui.theme.Grey2
import com.example.rentassistantapp.ui.theme.LightPink
import com.example.rentassistantapp.ui.theme.Red1

data class Message(
    val id: String,          // Message id
    val text: String,        // Message text
    val timestamp: String,     // Time when sent
    val isSentByUser: Boolean // true - User; false - Support (flag to determine who sent message)
)



@Composable
fun MessageBubble(message: Message) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalArrangement = if (message.isSentByUser) Arrangement.End else Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                // Limit maximal width
                .widthIn(max = 300.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = if (message.isSentByUser) 16.dp else 0.dp,
                        bottomEnd = if (message.isSentByUser) 0.dp else 16.dp
                    )
                )
                .background(
                    if (message.isSentByUser) BrightPink
                    else LightPink
                )
                .padding(12.dp)
        ) {
            Text(
                text = message.text,
                color = Color.Blue
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BubblePreview(){
    val mes = Message("1", "Hello", "16:02", isSentByUser = false)
    MessageBubble(
        mes
    )
}