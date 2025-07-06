package com.example.rentassistantapp.util

import android.content.Context
import android.content.SharedPreferences

object PrefsHelper {
    private const val KEY_JWT = "jwt"
    private const val KEY_FIRST_LAUNCH = "first_launch"
    private const val KEY_FIRST_NAME = "first_name"
    private const val KEY_TG_ID = "telegram_id"
    private const val KEY_SUBSCRIPTION_TYPE = "subscription_type"
    private const val KEY_EXPIRE_DATE = "expire_date"

    fun saveJwt(context: Context, token: String) {
        prefs(context).edit().putString(KEY_JWT, token).apply()
    }

    fun getJwt(context: Context): String? =
        prefs(context).getString(KEY_JWT, null)

    fun saveSubscriptionType(context: Context, type: String) {
        prefs(context).edit().putString(KEY_SUBSCRIPTION_TYPE, type).apply()
    }

    fun getSubscriptionType(context: Context): String? =
        prefs(context).getString(KEY_SUBSCRIPTION_TYPE, null)

    fun saveExpireDate(context: Context, date: String) {
        prefs(context).edit().putString(KEY_EXPIRE_DATE, date).apply()
    }

    fun getExpireDate(context: Context): String? =
        prefs(context).getString(KEY_EXPIRE_DATE, null)

    fun markLaunched(context: Context) {
        prefs(context).edit().putBoolean(KEY_FIRST_LAUNCH, false).apply()
    }

    fun isFirstLaunch(context: Context): Boolean =
        prefs(context).getBoolean(KEY_FIRST_LAUNCH, true)

    fun clearAll(context: Context) {
        prefs(context).edit().clear().apply()
    }

    fun resetLaunchFlag(context: Context) {
        prefs(context).edit().putBoolean(KEY_FIRST_LAUNCH, true).apply()
    }

    fun saveFirstName(context: Context, name: String) {
        prefs(context).edit().putString(KEY_FIRST_NAME, name).apply()
    }

    fun getFirstName(context: Context): String? =
        prefs(context).getString(KEY_FIRST_NAME, null)

    fun saveTelegramId(context: Context, id: String) {
        prefs(context).edit().putString(KEY_TG_ID, id).apply()
    }

    fun getTelegramId(context: Context): String? =
        prefs(context).getString(KEY_TG_ID, null)

    private fun prefs(context: Context): SharedPreferences =
        context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
}