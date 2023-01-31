package com.ssafy.fundyou.di

import android.content.Context
import com.ssafy.fundyou.data.local.prefs.SearchKeywordPreference
import com.ssafy.fundyou.domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferenceModule {
    @Provides
    @Singleton
    fun provideSearchPreferences(@ApplicationContext context : Context) : SearchKeywordPreference{
        return SearchKeywordPreference(context)
    }
}