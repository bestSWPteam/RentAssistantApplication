package com.example.rentassistantapp.di

import android.content.Context
import com.example.rentassistantapp.data.api.AuthApi
import com.example.rentassistantapp.data.api.PaymentApi
import com.example.rentassistantapp.util.Config
import com.example.rentassistantapp.util.JwtInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    private const val BASE_URL = Config.API_BASE_URL

    private fun retrofit(ctx: Context): Retrofit {
        val client = OkHttpClient.Builder()
            .addInterceptor(JwtInterceptor(ctx))
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideAuthApi(ctx: Context): AuthApi = retrofit(ctx).create(AuthApi::class.java)
    fun providePaymentApi(ctx: Context): PaymentApi = retrofit(ctx).create(PaymentApi::class.java)
}
