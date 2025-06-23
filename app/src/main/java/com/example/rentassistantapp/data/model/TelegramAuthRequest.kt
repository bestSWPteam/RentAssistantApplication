package com.example.rentassistantapp.data.model

data class TelegramAuthRequest(
    val id: String,
    val first_name: String,
    val username: String,
    val auth_date: String,
    val hash: String
)
