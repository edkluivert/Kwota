package com.kluivert.kwota.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.kluivert.kwota.data.model.QuoteModel
import kotlinx.android.synthetic.main.quote_item.view.*

class QuoteViewHolder (itemView : View): RecyclerView.ViewHolder(itemView){
    fun bind(item: QuoteModel?) {
         itemView.tvAuthor.text = item!!.author
        itemView.tvQuote.text = item!!.text


    }
}