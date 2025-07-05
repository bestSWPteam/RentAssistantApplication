package com.example.rentassistantapp.data.api

import com.example.rentassistantapp.data.model.TelegramLoginRequest
import com.example.rentassistantapp.data.model.TelegramLoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/callback")
    suspend fun loginWithTelegram(
        @Body body: TelegramLoginRequest
    ): Response<TelegramLoginResponse>
}

