package com.ssafy.fundyou.data.remote.di

import com.ssafy.fundyou.data.remote.service.*
import com.ssafy.fundyou.data.util.AuthInterceptorClient
import com.ssafy.fundyou.data.util.NoAuthInterceptorClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ServiceModule {
    @Provides
    @Singleton
    fun provideAuthService(
        @NoAuthInterceptorClient retrofit: Retrofit
    ): AuthApiService {
        return retrofit.create(AuthApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideSearchService(
        @AuthInterceptorClient retrofit: Retrofit
    ): SearchApiService {
        return retrofit.create(SearchApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideItemApiService(@AuthInterceptorClient retrofit: Retrofit): ItemApiService = retrofit.create(ItemApiService::class.java)

    @Provides
    @Singleton
    fun provideUserApiService(@AuthInterceptorClient retrofit: Retrofit): UserApiService = retrofit.create(UserApiService::class.java)

    @Provides
    @Singleton
    fun provideWishListApiService(@AuthInterceptorClient retrofit: Retrofit): WishListApiService = retrofit.create(WishListApiService::class.java)

    @Provides
    @Singleton
    fun provideFundingApiService(@AuthInterceptorClient retrofit: Retrofit) : FundingApiService = retrofit.create(FundingApiService::class.java)

    @Provides
    @Singleton
    fun providePayApiService(@AuthInterceptorClient retrofit: Retrofit) : PayApiService = retrofit.create(PayApiService::class.java)

    @Provides
    @Singleton
    fun provideArApiService(@AuthInterceptorClient retrofit: Retrofit) : ArApiService = retrofit.create(ArApiService::class.java)
}