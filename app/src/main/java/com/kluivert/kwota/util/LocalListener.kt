package com.kluivert.kwota.util

import com.kluivert.kwota.data.model.QuoteModel


interface LocalListener {

    suspend fun unlikeListener(quote: QuoteModel, position: Int)

}