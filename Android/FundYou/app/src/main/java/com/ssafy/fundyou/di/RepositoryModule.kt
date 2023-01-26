package com.ssafy.fundyou.di

import com.ssafy.fundyou.data.remote.datasource.auth.AuthRemoteDataSourceImpl
import com.ssafy.fundyou.data.remote.repository.AuthRepositoryImpl
import com.ssafy.fundyou.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Provides
    @Singleton
    fun bindsAuthRepository(
        authRemoteDataSourceImpl: AuthRemoteDataSourceImpl
    ): AuthRepository{
        return AuthRepositoryImpl(authRemoteDataSourceImpl)
    }
}