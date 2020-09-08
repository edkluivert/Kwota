package com.kluivert.kwota.useCase

import com.kluivert.kwota.data.model.QuoteModel
import com.kluivert.kwota.data.network.state.DataState
import com.kluivert.kwota.data.repository.DataRepository
import retrofit2.Response
import javax.inject.Inject

class DataUseCase
@Inject
constructor(
    private val dataRepository: DataRepository
){

    suspend fun getQuotesList() : DataState<Response<List<QuoteModel>>> {

        val quotesRepository = dataRepository.getQuotes()

       val resultData = if (quotesRepository.isSuccessful){
            DataState.Success(quotesRepository)
        }else{
            DataState.Failed(quotesRepository.errorBody().toString())

        }
        return resultData
    }

}