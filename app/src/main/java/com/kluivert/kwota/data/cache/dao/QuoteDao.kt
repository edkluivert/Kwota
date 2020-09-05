package com.kluivert.kwota.data.cache.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kluivert.kwota.data.cache.model.LocalQuoteEntity


@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuote(localQuoteEntity: LocalQuoteEntity) : Long

    @Delete
    suspend fun deleteQuote(localQuoteEntity: LocalQuoteEntity)

    @Query("SELECT * FROM quotes_table ORDER BY id DESC")
    fun readQuote() : LiveData<List<LocalQuoteEntity>>


}