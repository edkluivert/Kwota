package com.kluivert.kwota.util

import androidx.recyclerview.widget.DiffUtil
import com.kluivert.kwota.data.model.QuoteModel


class KwotaDiffUtil(
    private val oldList: MutableList<QuoteModel>,
    private val newList: MutableList<QuoteModel>
) : DiffUtil.Callback() {


    override fun getOldListSize() = oldList.size


    override fun getNewListSize() = newList.size


    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int)
            = oldList[oldItemPosition].id == newList[newItemPosition].id


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}