package com.ssafy.fundyou.data.util

import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Response

//class AuthInterceptor() : Interceptor {
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val request = chain.request().newBuilder()
//            .addHeader("Authorization", "bearer " + preferences.accessToken).build()
//
//        return chain.proceed(request)
//    }
//}