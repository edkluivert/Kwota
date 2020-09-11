package com.kluivert.kwota.ui.adapter

import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kluivert.kwota.data.model.QuoteModel
import com.kluivert.kwota.databinding.LocalQuoteItemBinding
import com.kluivert.kwota.databinding.QuoteItemBinding
import com.kluivert.kwota.util.KwotaDiffUtil
import com.kluivert.kwota.util.KwotaListener
import com.kluivert.kwota.util.LocalListener
import com.like.IconType
import com.like.LikeButton
import com.like.OnLikeListener
import kotlinx.android.synthetic.main.local_quote_item.view.*
import kotlinx.android.synthetic.main.quote_item.view.*
import kotlinx.android.synthetic.main.quote_item.view.imgLike
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class LocalQuoteAdapter(
    val quotelist: MutableList<QuoteModel>,
    var listener: LocalListener
) : RecyclerView.Adapter<LocalQuoteAdapter.LocalQuoteAdapterViewHolder>() {

    fun updateListItems(newList: MutableList<QuoteModel>) {

        val diffCallback = KwotaDiffUtil(quotelist, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        quotelist.clear()
          quotelist.addAll(newList)

        diffResult.dispatchUpdatesTo(this)
    }


    inner class LocalQuoteAdapterViewHolder(itemView: LocalQuoteItemBinding):RecyclerView.ViewHolder(itemView.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocalQuoteAdapterViewHolder {
        val view : LocalQuoteItemBinding = LocalQuoteItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LocalQuoteAdapterViewHolder(view)

    }

    override fun onBindViewHolder(holder: LocalQuoteAdapterViewHolder, position: Int) {
     holder.itemView.apply {
         val current = quotelist[position]
         localtvAuthor.text = current.author
         localtvQuote.text = current.text


         localimgLike.setOnClickListener {
                GlobalScope.launch {  listener.unlikeListener(quotelist[position],position)

             }

         }
     }
    }

    override fun getItemCount(): Int {
        return quotelist.size
    }

}