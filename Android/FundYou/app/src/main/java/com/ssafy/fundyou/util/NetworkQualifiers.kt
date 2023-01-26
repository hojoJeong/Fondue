package com.ssafy.fundyou.util

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthInterceptorClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NoAuthInterceptorClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GoogleInterceptorClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class KakaoInterceptorClient