package com.ssafy.fundyou.data.remote.di

import com.ssafy.fundyou.data.local.prefs.AuthSharePreference
import com.ssafy.fundyou.data.local.prefs.SearchKeywordPreference
import com.ssafy.fundyou.data.remote.datasource.auth.AuthRemoteDataSourceImpl
import com.ssafy.fundyou.data.remote.datasource.search.SearchRemoteDataSourceImpl
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
        authRemoteDataSourceImpl: AuthRemoteDataSourceImpl,
        authSharePreference: AuthSharePreference
    ): AuthRepository{
        return AuthRepositoryImpl(authRemoteDataSourceImpl, authSharePreference)
    }

    @Provides
    @Singleton
    fun provideSearchRepository(
        searchKeywordPreference: SearchKeywordPreference,
        searchRemoteDataSourceImpl: SearchRemoteDataSourceImpl
    ) : SearchRepository{
        return SearchRepositoryImpl(searchKeywordPreference, searchRemoteDataSourceImpl)
    }

    @Provides
    @Singleton
    fun provideItemRepository() : ItemRepository = ItemRepositoryImpl()
}