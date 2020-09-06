package com.kluivert.kwota.util

import androidx.recyclerview.widget.DiffUtil
import com.kluivert.kwota.data.model.QuoteModel



class KwotaDiffUtil : DiffUtil.ItemCallback<QuoteModel>() {
    override fun areItemsTheSame(oldItem: QuoteModel, newItem: QuoteModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: QuoteModel, newItem: QuoteModel): Boolean {
        return oldItem.id == newItem.id
    }
}