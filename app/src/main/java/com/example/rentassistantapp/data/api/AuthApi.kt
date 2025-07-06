package com.example.rentassistantapp.data.api

import com.example.rentassistantapp.data.model.TelegramAuthRequest
import com.example.rentassistantapp.data.model.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/callback")
    suspend fun telegramLogin(
        @Body body: TelegramAuthRequest
    ): Response<AuthResponse>
}