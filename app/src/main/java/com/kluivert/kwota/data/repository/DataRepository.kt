package com.kluivert.kwota.data.repository

import com.kluivert.kwota.data.model.QuoteModel
import com.kluivert.kwota.data.network.api.QuoteApi
import retrofit2.Response
import javax.inject.Inject

class DataRepository
@Inject
constructor(
    private val quoteApi: QuoteApi
){

    suspend fun getQuotes() : Response<List<QuoteModel>>{
       return quoteApi.getQuotes()
    }

}