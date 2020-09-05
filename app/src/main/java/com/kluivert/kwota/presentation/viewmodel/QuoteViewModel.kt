package com.kluivert.kwota.presentation.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.kluivert.kwota.data.repository.QuoteRepository
import com.kluivert.kwota.domain.entities.Quote
import com.kluivert.kwota.domain.state.DataState
import com.kluivert.kwota.domain.state.MainState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


class QuoteViewModel
@ViewModelInject
constructor(

    private val quoteRepository: QuoteRepository,
    @Assisted private val savedStateHandle: SavedStateHandle

) : ViewModel(){

    private val _dataState : MutableLiveData<DataState<List<Quote>>> = MutableLiveData()

    val dataState : LiveData<DataState<List<Quote>>>
    get() = _dataState

    fun setQuoteEvent(mainState: MainState){

        viewModelScope.launch (Dispatchers.IO){

            when(mainState){
                is MainState.GetQuoteEvents->{
                    quoteRepository.getQuotes()
                        .onEach {dataState->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }
            }
        }

    }





}