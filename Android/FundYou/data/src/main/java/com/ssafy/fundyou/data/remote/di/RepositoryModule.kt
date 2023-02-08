package com.ssafy.fundyou.data.remote.di

import com.ssafy.fundyou.data.local.prefs.AuthSharePreference
import com.ssafy.fundyou.data.local.prefs.SearchKeywordPreference
import com.ssafy.fundyou.data.remote.datasource.auth.AuthRemoteDataSource
import com.ssafy.fundyou.data.remote.datasource.item.ItemRemoteDataSource
import com.ssafy.fundyou.data.remote.datasource.search.SearchRemoteDataSource
import com.ssafy.fundyou.data.remote.repository.AuthRepositoryImpl
import com.ssafy.fundyou.data.remote.repository.ItemRepositoryImpl
import com.ssafy.fundyou.data.remote.repository.SearchRepositoryImpl
import com.ssafy.fundyou.domain.repository.AuthRepository
import com.ssafy.fundyou.domain.repository.ItemRepository
import com.ssafy.fundyou.domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal object RepositoryModule {
    @Provides
    @Singleton
    fun provideAuthRepository(
        authRemoteDataSource: AuthRemoteDataSource,
        authSharePreference: AuthSharePreference
    ): AuthRepository {
        return AuthRepositoryImpl(authRemoteDataSource, authSharePreference)
    }

    @Provides
    @Singleton
    fun provideSearchRepository(
        searchKeywordPreference: SearchKeywordPreference,
        searchRemoteDataSource: SearchRemoteDataSource
    ): SearchRepository {
        return SearchRepositoryImpl(searchKeywordPreference, searchRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideItemRepository(itemRemoteDataSource: ItemRemoteDataSource): ItemRepository =
        ItemRepositoryImpl(itemRemoteDataSource)
}