package com.kluivert.kwota.data.repository

import androidx.lifecycle.LiveData
import com.kluivert.kwota.data.cache.dao.QuoteDao
import com.kluivert.kwota.data.cache.mapper.CacheMapper
import com.kluivert.kwota.data.cache.model.LocalQuoteEntity
import com.kluivert.kwota.data.network.api.QuoteApi
import com.kluivert.kwota.data.network.mapper.NetworkMapper
import com.kluivert.kwota.domain.entities.Quote
import com.kluivert.kwota.domain.state.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class QuoteRepository
constructor(
    private val quoteDao: QuoteDao,
    private val quoteApi: QuoteApi,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper

) {
    private var readNote : LiveData<List<LocalQuoteEntity>> = quoteDao.readQuote()


    suspend fun getQuotes() : Flow<DataState<List<Quote>>> = flow {
        emit(DataState.Loading)
        delay(1000)

        try {
            val api = quoteApi.getQuotes()
            val  quotes = networkMapper.mapFromEntityList(api)

            emit(DataState.Success(networkMapper.mapFromEntityList(api)))
        }catch (e:Exception){
            emit(DataState.Error(e))
        }
    }

    suspend fun addQuote(localQuoteEntity: LocalQuoteEntity) {
       quoteDao.addQuote(localQuoteEntity)
    }


    suspend fun removeQuote(localQuoteEntity: LocalQuoteEntity){
        quoteDao.deleteQuote(localQuoteEntity)
    }

}