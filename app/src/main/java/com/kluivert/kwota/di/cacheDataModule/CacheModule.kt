package com.kluivert.kwota.di.cacheDataModule

import android.content.Context
import androidx.room.Room
import com.kluivert.kwota.data.cache.dao.QuoteDao
import com.kluivert.kwota.data.cache.database.QuoteDatabase
import com.kluivert.kwota.data.repository.LocalDbRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object CacheModule {

    @Provides
    fun providesQuoteDao(@ApplicationContext appContext: Context) : QuoteDao {
        return QuoteDatabase.getInstance(appContext).quoteDao
    }

    @Provides
    fun providesQuoteDBRepository(quoteDao: QuoteDao) =LocalDbRepository(quoteDao)

}

