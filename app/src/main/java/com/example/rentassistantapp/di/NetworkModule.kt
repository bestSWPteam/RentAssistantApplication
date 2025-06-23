package com.example.rentassistantapp.di

import android.content.Context
import com.example.rentassistantapp.util.Config
import com.example.rentassistantapp.util.JwtInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    fun provideRetrofit(ctx: Context): Retrofit {
        val client = OkHttpClient.Builder()
            .addInterceptor(JwtInterceptor(ctx))
            .build()
        return Retrofit.Builder()
            .baseUrl(Config.API_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
