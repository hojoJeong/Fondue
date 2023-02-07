package com.ssafy.fundyou.data.remote.di


import com.ssafy.fundyou.data.remote.datasource.auth.AuthRemoteDataSourceImpl
import com.ssafy.fundyou.data.remote.datasource.item.ItemRemoteDataSource
import com.ssafy.fundyou.data.remote.datasource.item.ItemRemoteDataSourceImpl
import com.ssafy.fundyou.data.remote.datasource.search.SearchRemoteDataSourceImpl
import com.ssafy.fundyou.data.remote.service.AuthApiService
import com.ssafy.fundyou.data.remote.service.ItemApiService
import com.ssafy.fundyou.data.remote.service.SearchApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataSourceModule {
    @Provides
    @Singleton
    fun provideAuthRemoteDataSource(
        authApiService: AuthApiService
    ): AuthRemoteDataSourceImpl = AuthRemoteDataSourceImpl(authApiService)

    @Provides
    @Singleton
    fun provideSearchRemoteDataSource(
        searchApiService: SearchApiService
    ): SearchRemoteDataSourceImpl = SearchRemoteDataSourceImpl(searchApiService)

    @Provides
    @Singleton
    fun provideItemRemoteDataSource(itemApiService: ItemApiService) : ItemRemoteDataSource = ItemRemoteDataSourceImpl(itemApiService)
}