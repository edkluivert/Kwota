package com.kluivert.kwota.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.kluivert.kwota.data.model.QuoteModel
import com.kluivert.kwota.data.network.state.DataState
import com.kluivert.kwota.useCase.DataUseCase

class QuoteViewModel
@ViewModelInject
constructor(private val dataUseCase: DataUseCase) : ViewModel(){

    fun getQuotesList() : LiveData<DataState<List<QuoteModel>>>{

      return  liveData<DataState<List<QuoteModel>>> {
           emit(DataState.Loading())
          emit(dataUseCase.getQuotesList())
        }
    }

}