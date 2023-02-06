package com.ssafy.fundyou.di

import com.ssafy.fundyou.data.remote.service.AuthApiService
import com.ssafy.fundyou.data.remote.service.ItemApiService
import com.ssafy.fundyou.util.network.NoAuthInterceptorClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideAuthService(
        @NoAuthInterceptorClient retrofit: Retrofit
    ): AuthApiService {
        return retrofit.create(AuthApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideItemService(
        @NoAuthInterceptorClient retrofit: Retrofit
    ): ItemApiService {
        return retrofit.create(ItemApiService::class.java)
    }

}