package com.example.rentassistantapp.messager

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MessageList(messages: List<Message>, modifier: Modifier = Modifier) {
    val listState = rememberLazyListState()

    // Go to the last message automatically
    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) {
            listState.animateScrollToItem(messages.size - 1)
        }
    }

    LazyColumn(
        state = listState,
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 8.dp),
    ) {
        items(messages, key = { it.id }) { message ->
            MessageBubble(message = message)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {

    // Creating test data
    val testMessages = List(50) { index ->
        Message(
            text = "Это сообщение номер ${index + 1}. ".repeat( (2..7).random() ),
            isSentByUser = index % 2 != 0,
            id = index.toString(),
            timestamp = "-"
        )
    }

    MessageList(messages = testMessages)
}