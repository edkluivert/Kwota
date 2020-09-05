package com.kluivert.kwota.presentation.di.repositoryModule

import com.kluivert.kwota.data.cache.dao.QuoteDao
import com.kluivert.kwota.data.cache.mapper.CacheMapper
import com.kluivert.kwota.data.network.api.QuoteApi
import com.kluivert.kwota.data.network.mapper.NetworkMapper
import com.kluivert.kwota.data.repository.QuoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun ProvideQuoteRepository(
         quoteDao: QuoteDao,
         quoteApi: QuoteApi,
         cacheMapper: CacheMapper,
         networkMapper: NetworkMapper
    ):QuoteRepository{
       return QuoteRepository(
           quoteDao,
           quoteApi,
           cacheMapper,
           networkMapper
       )

    }

}