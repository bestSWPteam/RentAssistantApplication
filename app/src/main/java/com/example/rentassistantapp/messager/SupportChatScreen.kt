package com.example.rentassistantapp.messager

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SupportChatScreen(
    // Get ViewModel
    chatViewModel: ChatViewModel = viewModel()
) {
    // Listen to changes viewModel messages list
    val messages by chatViewModel.messages.collectAsState()

    Scaffold(
        // Upper panel
        topBar = {
            TopAppBar(title = { Text("Поддержка") })
        },
        // Down panel (to write the message)
        bottomBar = {
            MessageInput(
                onMessageSend = { text ->
                    // If message was written and sent it passes viewModel
                    chatViewModel.sendMessage(text)
                }
            )
        }
    ) { innerPadding ->
        // The content itself (Messages list)
        MessageList(
            messages = messages,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SupportChatScreenPreview() {
    SupportChatScreen()
}