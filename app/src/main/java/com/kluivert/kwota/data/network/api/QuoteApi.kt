package com.kluivert.kwota.data.network.api

import com.kluivert.kwota.data.model.QuoteModel
import com.kluivert.kwota.util.constants.Constants
import retrofit2.Response
import retrofit2.http.GET

interface QuoteApi {

    @GET(Constants.QUOTE_PAGE_REQUEST)
    suspend fun getQuotes() : Response<List<QuoteModel>>

}