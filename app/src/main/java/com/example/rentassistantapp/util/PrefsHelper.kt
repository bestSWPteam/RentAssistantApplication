package com.example.rentassistantapp.util

import android.content.Context

object PrefsHelper {
    private const val PREFS_NAME = "app_prefs"
    private const val KEY_JWT = "jwt_token"

    fun saveJwt(ctx: Context, token: String) {
        ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_JWT, token)
            .apply()
    }

    fun getJwt(ctx: Context): String? =
        ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getString(KEY_JWT, null)
}