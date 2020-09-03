package com.kluivert.kwota.data.network.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NetworkQuoteEntity(

    @SerializedName("author")
    @Expose
    val author : String,

    @SerializedName("text")
    @Expose
    val text : String
)