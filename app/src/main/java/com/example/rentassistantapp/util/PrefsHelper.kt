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
    private const val KEY_USERNAME = "username"
    private const val KEY_LAST_NAME = "last_name"
    private const val KEY_AVATAR_URL = "avatar_url"

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

    fun saveLastName(ctx: Context, lastName: String) {
        prefs(ctx).edit().putString(KEY_LAST_NAME, lastName).apply()
    }

    fun getLastName(ctx: Context): String? {
        return prefs(ctx).getString(KEY_LAST_NAME, null)
    }

    fun saveAvatarUrl(ctx: Context, avatarUrl: String) {
        prefs(ctx).edit().putString(KEY_AVATAR_URL, avatarUrl).apply()
    }

    fun getAvatarUrl(ctx: Context): String? {
        return prefs(ctx).getString(KEY_AVATAR_URL, null)
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

    fun saveUsername(ctx: Context, username: String) {
        prefs(ctx).edit().putString(KEY_USERNAME, username).apply()
    }

    fun getUsername(ctx: Context): String? {
        return prefs(ctx).getString(KEY_USERNAME, null)
    }

    fun saveTelegramUser(ctx: Context, id: String, firstName: String, username: String) {
        saveTelegramId(ctx, id)
        saveFirstName(ctx, firstName)
        saveUsername(ctx, username)
    }

    private fun prefs(context: Context): SharedPreferences =
        context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
}