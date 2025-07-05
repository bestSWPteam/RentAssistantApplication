package com.example.rentassistantapp.util

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class JwtInterceptor(private val ctx: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = PrefsHelper.getJwt(ctx)
        val request = chain.request().newBuilder().apply {
            if (!token.isNullOrEmpty()) {
                header("X-Auth-Token", token)
            }
        }.build()
        return chain.proceed(request)
    }
}
