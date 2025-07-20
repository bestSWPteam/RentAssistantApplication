package com.example.rentassistantapp.util

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class JwtInterceptor(private val ctx: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val jwt = PrefsHelper.getJwt(ctx).orEmpty()
        val tgId = PrefsHelper.getTelegramId(ctx).orEmpty()
        val req = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $jwt")
            .addHeader("X-Telegram-ID", tgId)
            .addHeader("X-Auth-Token", jwt)
            .build()
        return chain.proceed(req)
    }
}
