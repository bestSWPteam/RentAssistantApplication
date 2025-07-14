package com.example.rentassistantapp.data.api

import com.example.rentassistantapp.data.model.CodeResponse
import com.example.rentassistantapp.data.model.LoginStatusResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface AuthApi {
    @POST("/auth/prepare-login")
    suspend fun prepareLogin(): CodeResponse

    @GET("/auth/status/{code}")
    suspend fun checkLoginStatus(@Path("code") code: String): Response<LoginStatusResponse>
}


