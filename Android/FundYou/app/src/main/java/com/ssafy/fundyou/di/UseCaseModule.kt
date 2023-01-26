package com.ssafy.fundyou.di

import com.ssafy.fundyou.domain.repository.AuthRepository
import com.ssafy.fundyou.domain.usecase.auth.GetGoogleAuthUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetGoogleAuthUseCase(authRepository: AuthRepository) : GetGoogleAuthUseCase{
        return GetGoogleAuthUseCase(authRepository)
    }
}