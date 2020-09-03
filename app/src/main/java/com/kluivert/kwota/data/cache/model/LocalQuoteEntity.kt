package com.kluivert.kwota.data.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes_table")
data class LocalQuoteEntity(

    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "id")
    val id : Int,

    @ColumnInfo(name = "author")
    val author : String,

    @ColumnInfo(name = "text")
    val text : String


)