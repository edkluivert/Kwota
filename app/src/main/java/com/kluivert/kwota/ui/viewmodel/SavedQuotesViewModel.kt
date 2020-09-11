package com.kluivert.kwota.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.kluivert.kwota.data.model.QuoteModel
import com.kluivert.kwota.data.network.state.DataState
import com.kluivert.kwota.data.repository.LocalDbRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SavedQuotesViewModel
@ViewModelInject constructor(private val localDbRepository: LocalDbRepository) :
    ViewModel() ,LifecycleObserver {

    private val insertedId = MutableLiveData<Long>()
    var quoteFinalList: LiveData<MutableList<QuoteModel>> =
        MutableLiveData<MutableList<QuoteModel>>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun fetchStudentData() {
        viewModelScope.launch {
            quoteFinalList = localDbRepository.fetchDbQuotes()
        }
    }

    fun addQuote(quoteModel: QuoteModel) {
        viewModelScope.launch {

            val userId: Long = localDbRepository.addQuote(quoteModel)
            insertedId.postValue(userId)
        }
    }

    fun removeQuote(quoteModel: QuoteModel) {
        viewModelScope.launch {
            localDbRepository.deleteQuote(quoteModel)
        }
    }
}





