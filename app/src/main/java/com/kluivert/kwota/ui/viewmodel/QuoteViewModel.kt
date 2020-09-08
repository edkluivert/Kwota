package com.kluivert.kwota.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.kluivert.kwota.data.cache.database.QuoteDatabase
import com.kluivert.kwota.data.model.QuoteModel
import com.kluivert.kwota.data.network.state.DataState
import com.kluivert.kwota.data.repository.LocalDbRepository
import com.kluivert.kwota.useCase.DataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.launch
import retrofit2.Response

class QuoteViewModel
@ViewModelInject
constructor(private val dataUseCase: DataUseCase) : ViewModel(){

    val _myResponse : MutableLiveData<DataState<Response<List<QuoteModel>>>> = MutableLiveData()





    fun setQuoteEvents(){
          viewModelScope.launch {
              DataState.Loading()
              val response = dataUseCase.getQuotesList()
              _myResponse.value = response

          }

    }

    fun getQuotesList() : LiveData<DataState<Response<List<QuoteModel>>>>{
      return  liveData<DataState<Response<List<QuoteModel>>>> {
           emit(DataState.Loading())
          emit(dataUseCase.getQuotesList())
        }
    }

    fun addQuote(quoteModel: QuoteModel){

    }



}
