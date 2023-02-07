package com.ssafy.fundyou.data.util

import com.ssafy.fundyou.data.local.prefs.AuthSharePreference
import okhttp3.Interceptor
import okhttp3.Response

internal class AuthInterceptor(private val authSharePreference: AuthSharePreference) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "bearer " + authSharePreference.accessToken).build()

        return chain.proceed(request)
    }
}