package com.kluivert.kwota.data.repository

import com.kluivert.kwota.data.cache.dao.QuoteDao
import com.kluivert.kwota.data.model.QuoteModel
import javax.inject.Inject

class LocalDbRepository
@Inject
constructor(

    private val quoteDao: QuoteDao

){

    suspend fun addQuote(quoteModel: QuoteModel){
        quoteDao.addQuote(quoteModel)
    }

    suspend fun deleteQuote(quoteModel: QuoteModel){
        quoteDao.deleteQuote(quoteModel)
    }

    suspend fun fetchDbQuotes() = quoteDao.readQuote()




}