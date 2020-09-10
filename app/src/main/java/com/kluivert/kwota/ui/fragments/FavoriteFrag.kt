package com.kluivert.kwota.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.kluivert.kwota.data.model.QuoteModel
import com.kluivert.kwota.databinding.FragmentFavoriteBinding
import com.kluivert.kwota.databinding.FragmentQuoteListBinding
import com.kluivert.kwota.ui.adapter.LocalQuoteAdapter
import com.kluivert.kwota.ui.adapter.QuoteAdapter
import com.kluivert.kwota.ui.viewmodel.QuoteViewModel
import com.kluivert.kwota.ui.viewmodel.SavedQuotesViewModel
import com.kluivert.kwota.util.DividerItemDecoration
import com.kluivert.kwota.util.KwotaListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFrag : Fragment(),KwotaListener,LifecycleOwner {

    private var _binding: FragmentFavoriteBinding? = null
    private val quotesavedbinding get() = _binding!!
    var quotelist: MutableList<QuoteModel> = mutableListOf()
    private val localquoteViewModel : SavedQuotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val view = quotesavedbinding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.lifecycle.addObserver(localquoteViewModel)
       // localquoteViewModel.fetchStudentData()
        setHasOptionsMenu(true)
        val adapter = LocalQuoteAdapter(quotelist,this)
        quotesavedbinding.localRecycler.adapter = adapter
        quotesavedbinding.localRecycler.addItemDecoration(
            DividerItemDecoration(requireContext(),
                LinearLayoutManager.VERTICAL)
        )


        localquoteViewModel.quoteFinalList.observe(viewLifecycleOwner, Observer {
              adapter.updateListItems(it)
        })

        quotesavedbinding.localRecycler.smoothScrollToPosition(0)

    }

    override suspend fun likelistener(quote: QuoteModel, position: Int) {

    }

    override suspend fun unlikeListener(quote: QuoteModel, position: Int) {
      localquoteViewModel
    }




}