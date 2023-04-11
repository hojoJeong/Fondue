package com.ssafy.fundyou.data.remote.di

import com.ssafy.fundyou.data.local.prefs.AuthSharePreference
import com.ssafy.fundyou.data.local.prefs.SearchKeywordPreference
import com.ssafy.fundyou.data.remote.datasource.auth.AuthRemoteDataSource
import com.ssafy.fundyou.data.remote.datasource.funding.FundingDataSource
import com.ssafy.fundyou.data.remote.datasource.item.ItemRemoteDataSource
import com.ssafy.fundyou.data.remote.datasource.search.SearchRemoteDataSource
import com.ssafy.fundyou.data.remote.datasource.user.UserRemoteDataSource
import com.ssafy.fundyou.data.remote.datasource.wishlist.WishListRemoteDataSource
import com.ssafy.fundyou.data.remote.repository.*
import com.ssafy.fundyou.data.remote.repository.AuthRepositoryImpl
import com.ssafy.fundyou.data.remote.repository.ItemRepositoryImpl
import com.ssafy.fundyou.data.remote.repository.SearchRepositoryImpl
import com.ssafy.fundyou.data.remote.repository.UserRepositoryImpl
import com.ssafy.fundyou.domain.repository.*
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

    @Provides
    @Singleton
    fun provideUserRepository(userRemoteDataSource: UserRemoteDataSource): UserRepository =
        UserRepositoryImpl(userRemoteDataSource)

    @Provides
    @Singleton
    fun provideWishRepository(wishListRemoteDataSource: WishListRemoteDataSource): WishListRepository =
        WishListRepositoryImpl(wishListRemoteDataSource)

    @Provides
    @Singleton
    fun provideFundingRepository(fundingDataSource: FundingDataSource) : FundingRepository = FundingRepositoryImpl(fundingDataSource)
}