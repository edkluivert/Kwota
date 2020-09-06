package com.kluivert.kwota.data.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kluivert.kwota.data.cache.dao.QuoteDao
import com.kluivert.kwota.data.cache.model.LocalQuoteEntity
import com.kluivert.kwota.data.model.QuoteModel

@Database(entities = [QuoteModel::class],version = 1, exportSchema = true)
abstract class QuoteDatabase : RoomDatabase(){

    abstract fun quoteDao() : QuoteDao

    companion object{
        const val DATABASE_NAME : String = "quotes_db"
    }

}

