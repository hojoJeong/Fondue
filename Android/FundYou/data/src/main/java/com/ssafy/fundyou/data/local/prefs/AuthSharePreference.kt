package com.ssafy.fundyou.data.local.prefs

import android.content.Context

internal class AuthSharePreference(context: Context) {

    private val prefs = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    var accessToken: String?
        get() = prefs.getString("accessToken", null)
        set(value) = prefs.edit().putString("accessToken", value).apply()

    var refreshToken: String?
        get() = prefs.getString("refreshToken", null)
        set(value) = prefs.edit().putString("refreshToken", value).apply()

    fun clearAuthPreference() {
        prefs.edit().clear().apply()
    }
}