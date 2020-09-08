package com.kluivert.kwota.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.kluivert.kwota.data.model.QuoteModel
import com.kluivert.kwota.data.network.state.DataState
import com.kluivert.kwota.databinding.FragmentQuoteListBinding
import com.kluivert.kwota.util.DividerItemDecoration
import com.kluivert.kwota.ui.adapter.QuoteAdapter
import com.kluivert.kwota.ui.viewmodel.QuoteViewModel
import com.kluivert.kwota.util.KwotaListener
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class QuoteListFrag : Fragment(),KwotaListener {

    private var noteClicked : Int = -1
    private var _binding: FragmentQuoteListBinding? = null
    var quotelist: MutableList<QuoteModel> = mutableListOf()
    private val quotelistbinding get() = _binding!!

    private val quoteViewModel : QuoteViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuoteListBinding.inflate(inflater, container, false)
        val view = quotelistbinding.root

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val quotesList = quoteViewModel.getQuotesList()
        quoteViewModel.setQuoteEvents()

        val adapter = QuoteAdapter()
        quotelistbinding.listQuoteRecycler.adapter = adapter
        quotelistbinding.listQuoteRecycler.addItemDecoration(DividerItemDecoration(requireContext(),LinearLayoutManager.VERTICAL))


        quoteViewModel._myResponse.observe(viewLifecycleOwner, Observer {dataState->
            when(dataState){

                is DataState.Loading->{
                    displayProgressBar(true)
                }

                is DataState.Success ->{

                    displayProgressBar(false)
                    //val quotesResult = dataState.data
                    adapter.submitList(dataState.data!!.body())
                }
                is DataState.Failed->{
                    displayProgressBar(false)
                    displayError(dataState.message.toString())

                }

                is DataState.Exception->{
                    displayProgressBar(false)
                }
            }
        })


     /*  quotesList.observe(viewLifecycleOwner, Observer {dataState->

            when(dataState){

                is DataState.Loading->{
                            displayProgressBar(true)
                }

                is DataState.Success ->{

                       displayProgressBar(false)
                    //val quotesResult = dataState.data
                     adapter.submitList(dataState.data!!.body())
                }
                is DataState.Failed->{
                         displayProgressBar(false)
                         displayError(dataState.message.toString())

                }

                is DataState.Exception->{
                           displayProgressBar(false)
                }
            }

        })*/

    }




    @SuppressLint("SetTextI18n")
    private fun displayError(message: String) {

        if (message != null) {
            quotelistbinding.tvError.text = message
        } else {
            quotelistbinding.tvError.text = "Unknown error"
        }

    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        quotelistbinding.progBar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun likelistener(quote: QuoteModel, position: Int) {

    }

    override suspend fun unlikeListener(quote: QuoteModel, position: Int) {

    }

}