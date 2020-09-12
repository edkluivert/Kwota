package com.kluivert.kwota.data.cache.dao


import androidx.lifecycle.LiveData
import androidx.room.*
import com.kluivert.kwota.data.model.QuoteModel


@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addQuote(quoteModel: QuoteModel) : Long

    @Delete
    suspend fun deleteQuote(quoteModel: QuoteModel)

    @Query("SELECT * FROM quotes_table ORDER BY id DESC")
    fun readQuote() : LiveData<MutableList<QuoteModel>>



}