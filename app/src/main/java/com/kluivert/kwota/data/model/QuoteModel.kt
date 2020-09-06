package com.kluivert.kwota.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "quotes_table")
data class QuoteModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int,

    @SerializedName("author")
    @ColumnInfo(name = "author")
    val author : String,


    @SerializedName("text")
    @ColumnInfo(name = "text")
    val text : String
)