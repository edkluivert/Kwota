package com.kluivert.kwota.data.cache.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kluivert.kwota.data.cache.dao.QuoteDao
import com.kluivert.kwota.data.model.QuoteModel

@Database(entities = [QuoteModel::class],version = 1, exportSchema = true)
abstract class QuoteDatabase : RoomDatabase(){

    abstract val quoteDao: QuoteDao

    companion object {

        @Volatile
        private var INSTANCE: QuoteDatabase? = null

        fun getInstance(context: Context): QuoteDatabase {

            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        QuoteDatabase::class.java,
                        "quote_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}

