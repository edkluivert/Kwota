package com.kluivert.kwota.presentation.di.localDataModule

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
object LocalModule {

   @Singleton
   @Provides
   fun provideLocalDb(@ApplicationContext context: Context) : QuoteDatabase {

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
    fun provideLocalDao(quoteDatabase: QuoteDatabase) : QuoteDao{
        return quoteDatabase.quoteDao()
    }

}