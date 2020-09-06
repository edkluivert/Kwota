package com.kluivert.kwota.data.network.state

 sealed class DataState<out T> {

     data class Success<out T>(val data : T? = null) : DataState<T>()

     data class Loading(val nothing: Nothing? = null) : DataState<Nothing>()

     data class Failed(val message : String? = null):DataState<Nothing>()

     data class Exception(val message: String? = null):DataState<Nothing>()

}