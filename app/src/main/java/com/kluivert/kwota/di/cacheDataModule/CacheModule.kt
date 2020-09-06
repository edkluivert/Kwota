package com.kluivert.kwota.di.cacheDataModule

import android.content.Context
import androidx.room.Room
import com.kluivert.kwota.data.cache.dao.QuoteDao
import com.kluivert.kwota.data.cache.database.QuoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun providesCacheData(@ApplicationContext context: Context) : QuoteDatabase{
               return Room.databaseBuilder(
                   context,
                   QuoteDatabase::class.java,
                   QuoteDatabase.DATABASE_NAME
               )
                   .fallbackToDestructiveMigration()
                   .build()
    }

    @Singleton
    @Provides
    fun providesQuoteDao(quoteDatabase: QuoteDatabase):QuoteDao{
        return quoteDatabase.quoteDao()
    }
}