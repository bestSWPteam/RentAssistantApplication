package com.example.rentassistantapp.util

import android.content.Context
import com.example.rentassistantapp.MainActivity

object PrefsHelper {
    private const val PREFS_NAME = "rent_assistant_prefs"
    private const val KEY_JWT = "jwt_token"
    private const val KEY_TELEGRAM_ID = "telegram_id"
    private const val KEY_FIRST_NAME = "first_name"
    private const val KEY_USERNAME = "username"
    private const val KEY_FIRST_LAUNCH = "first_launch"

    fun saveJwt(ctx: Context, jwt: String) {
        ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_JWT, jwt)
            .apply()
    }

    fun getJwt(ctx: Context): String? =
        ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getString(KEY_JWT, null)

    fun saveTelegramUser(ctx: Context, id: String, firstName: String, username: String) {
        ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_TELEGRAM_ID, id)
            .putString(KEY_FIRST_NAME, firstName)
            .putString(KEY_USERNAME, username)
            .apply()
    }

    fun getTelegramId(ctx: Context): String? =
        ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getString(KEY_TELEGRAM_ID, null)

    fun getFirstName(ctx: Context): String? =
        ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getString(KEY_FIRST_NAME, null)

    fun getUsername(ctx: Context): String? =
        ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getString(KEY_USERNAME, null)

    fun isFirstLaunch(ctx: Context): Boolean =
        ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getBoolean(KEY_FIRST_LAUNCH, true)

    fun markLaunched(ctx: Context) {
        ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putBoolean(KEY_FIRST_LAUNCH, false)
            .apply()
    }

    fun clearAll(ctx: Context) {
        ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .clear()
            .apply()
    }
}