package com.example.rentassistantapp.util

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class JwtInterceptor(private val ctx: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = PrefsHelper.getJwt(ctx)
        val req = if (token != null) {
            chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else chain.request()
        return chain.proceed(req)
    }
}