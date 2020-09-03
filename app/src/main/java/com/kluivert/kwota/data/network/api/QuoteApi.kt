package com.kluivert.kwota.data.network.api

import com.kluivert.kwota.data.network.model.NetworkQuoteEntity
import com.kluivert.kwota.util.constants.Constants
import retrofit2.http.GET

interface QuoteApi {

    @GET(Constants.QUOTE_PAGE_REQUEST)
    suspend fun getQuotes() : List<NetworkQuoteEntity>

}