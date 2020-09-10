package com.kluivert.kwota.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kluivert.kwota.data.model.QuoteModel
import com.kluivert.kwota.databinding.QuoteItemBinding
import com.kluivert.kwota.util.KwotaDiffUtil
import com.kluivert.kwota.util.KwotaListener
import com.like.IconType
import com.like.LikeButton
import com.like.OnLikeListener
import kotlinx.android.synthetic.main.quote_item.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class QuoteAdapter(
    val quotelist: MutableList<QuoteModel>,
    var listener: KwotaListener
) : RecyclerView.Adapter<QuoteAdapter.QuoteAdapterViewHolder>() {

    fun updateListItems(newList: MutableList<QuoteModel>) {

        val diffCallback = KwotaDiffUtil(quotelist, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        quotelist.clear()
          quotelist.addAll(newList)

        diffResult.dispatchUpdatesTo(this)
    }


    inner class QuoteAdapterViewHolder(itemView: QuoteItemBinding):RecyclerView.ViewHolder(itemView.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteAdapterViewHolder {
        val view : QuoteItemBinding = QuoteItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return QuoteAdapterViewHolder(view)

    }

    override fun onBindViewHolder(holder: QuoteAdapterViewHolder, position: Int) {
     holder.itemView.apply {
         val current = quotelist[position]
         tvAuthor.text = current.author
         tvQuote.text = current.text

         imgLike.setIcon(IconType.Heart)
         imgLike.setOnLikeListener(object : OnLikeListener {
             override fun liked(likeButton: LikeButton) {
                 GlobalScope.launch {  listener.likelistener(quotelist[position],position)}
                 Toast.makeText(context,"Saved",Toast.LENGTH_SHORT).show()
             }
             override fun unLiked(likeButton: LikeButton) {
                 GlobalScope.launch {  listener.unlikeListener(quotelist[position],position)}
                 Toast.makeText(context,"Removed",Toast.LENGTH_SHORT).show()
             }
         })

        /* imgLike.setOnClickListener {
              val checkState : Boolean? = null
             if (checkState != true){
                 GlobalScope.launch {  listener.likelistener(quotelist[position],position)}
              //   checkState
                 imgLike.setImageResource(R.drawable.heart)
                 Toast.makeText(context,"Saved",Toast.LENGTH_SHORT).show()
             }else{
                GlobalScope.launch {  listener.unlikeListener(quotelist[position],position)}
                // checkState
                 imgLike.setImageResource(R.drawable.like)
                 Toast.makeText(context,"Removed",Toast.LENGTH_SHORT).show()
             }

         }*/
     }
    }

    override fun getItemCount(): Int {
        return quotelist.size
    }

}