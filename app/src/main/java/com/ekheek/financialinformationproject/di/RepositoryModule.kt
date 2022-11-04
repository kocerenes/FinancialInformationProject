package com.ekheek.financialinformationproject.di

import com.ekheek.financialinformationproject.data.remote.NewsApi
import com.ekheek.financialinformationproject.data.repository.NewsRepositoryImpl
import com.ekheek.financialinformationproject.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideNewsRepository(api: NewsApi): NewsRepository = NewsRepositoryImpl(api)
}