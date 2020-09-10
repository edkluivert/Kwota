package com.kluivert.kwota.util

import com.kluivert.kwota.data.model.QuoteModel


interface KwotaListener {


    suspend fun likelistener(quote: QuoteModel, position: Int)

    suspend fun unlikeListener(quote: QuoteModel, position: Int)

}