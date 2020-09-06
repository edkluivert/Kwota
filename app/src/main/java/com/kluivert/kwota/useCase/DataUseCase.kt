package com.kluivert.kwota.useCase

import com.kluivert.kwota.data.model.QuoteModel
import com.kluivert.kwota.data.network.state.DataState
import com.kluivert.kwota.data.repository.DataRepository
import javax.inject.Inject

class DataUseCase
@Inject
constructor(
    private val dataRepository: DataRepository
){

    suspend fun getQuotesList() : DataState<List<QuoteModel>> {

        val quotesRepository = dataRepository.getQuotes()

        val resultData = if (!quotesRepository.isNullOrEmpty()){
            DataState.Success(quotesRepository)
        }else{
            DataState.Failed("Something went wrong. Please try again!")

        }
        return resultData
    }

}