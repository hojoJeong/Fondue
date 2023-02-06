package com.ssafy.fundyou.util.network

import android.content.SharedPreferences
import com.ssafy.fundyou.data.local.prefs.AuthSharePreference
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val authSharePreference: AuthSharePreference) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "bearer " + authSharePreference.accessToken).build()

        return chain.proceed(request)
    }
}