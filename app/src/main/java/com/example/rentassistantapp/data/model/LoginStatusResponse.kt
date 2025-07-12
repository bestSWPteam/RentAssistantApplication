package com.example.rentassistantapp.data.model

data class LoginStatusResponse(
    val token: String?,
    val id: String,
    val firstName: String,
    val username: String
)
