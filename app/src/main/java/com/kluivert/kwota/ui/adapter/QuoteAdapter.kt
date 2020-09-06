package com.kluivert.kwota.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kluivert.kwota.data.model.QuoteModel
import com.kluivert.kwota.util.KwotaDiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.kluivert.kwota.R


class QuoteAdapter : ListAdapter<QuoteModel, QuoteViewHolder>(KwotaDiffUtil()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quote_item,parent,false)
        return QuoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {

        val item = getItem(position)
        holder.bind(item)
    }


}


