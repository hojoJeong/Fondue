package com.ssafy.fundyou.di

import com.ssafy.fundyou.domain.repository.AuthRepository
import com.ssafy.fundyou.domain.repository.SearchRepository
import com.ssafy.fundyou.domain.usecase.auth.GetAccessTokenUseCase
import com.ssafy.fundyou.domain.usecase.auth.GetJWTByRefreshTokenUseCase
import com.ssafy.fundyou.domain.usecase.auth.GetKakaoAuthUseCase
import com.ssafy.fundyou.domain.usecase.auth.GetRefreshTokenUseCase
import com.ssafy.fundyou.domain.usecase.search.AddSearchHistoryKeyword
import com.ssafy.fundyou.domain.usecase.search.DeleteAllHistoryKeywordUseCase
import com.ssafy.fundyou.domain.usecase.search.DeleteSearchHistoryKeywordUseCase
import com.ssafy.fundyou.domain.usecase.search.GetSearchHistoryListUseCase
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
    fun provideGetJWTByRefreshTokenUseCase(authRepository: AuthRepository) : GetJWTByRefreshTokenUseCase{
        return GetJWTByRefreshTokenUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideGetRefreshTokenUseCase(authRepository: AuthRepository) : GetRefreshTokenUseCase{
        return GetRefreshTokenUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideGetAccessTokenUseCase(authRepository: AuthRepository) : GetAccessTokenUseCase{
        return GetAccessTokenUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideGetKakaoAuthUseCase(authRepository: AuthRepository) : GetKakaoAuthUseCase{
        return GetKakaoAuthUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteAllHistoryKeywordUseCase(searchRepository: SearchRepository): DeleteAllHistoryKeywordUseCase {
        return DeleteAllHistoryKeywordUseCase(searchRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteSearchHistoryKeyword(searchRepository: SearchRepository): DeleteSearchHistoryKeywordUseCase {
        return DeleteSearchHistoryKeywordUseCase(searchRepository)
    }

    @Provides
    @Singleton
    fun provideGetSearchHistoryListUseCase(searchRepository: SearchRepository): GetSearchHistoryListUseCase {
        return GetSearchHistoryListUseCase(searchRepository)
    }

    @Provides
    @Singleton
    fun provideAddSearchHistoryKeyword(searchRepository: SearchRepository): AddSearchHistoryKeyword {
        return AddSearchHistoryKeyword(searchRepository)
    }

}