package com.kluivert.kwota.ui.fragments

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.view.View.inflate
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.SnackbarContentLayout
import com.kluivert.kwota.R
import com.kluivert.kwota.data.model.QuoteModel
import com.kluivert.kwota.data.network.state.DataState
import com.kluivert.kwota.databinding.ActivityMainBinding.inflate
import com.kluivert.kwota.databinding.FragmentQuoteListBinding
import com.kluivert.kwota.ui.adapter.QuoteAdapter
import com.kluivert.kwota.ui.viewmodel.QuoteViewModel
import com.kluivert.kwota.ui.viewmodel.SavedQuotesViewModel
import com.kluivert.kwota.util.DividerItemDecoration
import com.kluivert.kwota.util.KwotaListener
import com.kluivert.kwota.util.internetChecker.CheckNetwork
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.quote_item.*
import java.util.zip.Inflater


@AndroidEntryPoint
class QuoteListFrag : Fragment(),KwotaListener {


    private var quoteClicked : Int = -1
    private var _binding: FragmentQuoteListBinding? = null
    var quotelist: MutableList<QuoteModel> = mutableListOf()
    private val quotelistbinding get() = _binding!!
    private val quoteViewModel : QuoteViewModel by viewModels()
    private val localquoteViewModel :  SavedQuotesViewModel by viewModels()
    lateinit var adapter : QuoteAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuoteListBinding.inflate(inflater, container, false)
        val view = quotelistbinding.root

        return view

    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      //  val quotesList = quoteViewModel.getQuotesList()
    quotelistbinding.btnRetry.visibility = View.GONE
        val myNetwork = CheckNetwork(requireContext())

        setHasOptionsMenu(true)

        myNetwork.isOnline
        if (myNetwork.isOnline){
            quoteViewModel.setQuoteEvents()
            getData()
            quotelistbinding.btnRetry.visibility = View.GONE
            quotelistbinding.tvError.visibility = View.GONE

        }else{
            displayProgressBar(false)
            quotelistbinding.btnRetry.visibility = View.VISIBLE
            quotelistbinding.tvError.text = "No Internet Connection!"


        }



        quotelistbinding.btnRetry.setOnClickListener {
            if(myNetwork.isOnline){
                displayProgressBar(true)
                quoteViewModel.setQuoteEvents()
                getData()
                quotelistbinding.btnRetry.visibility = View.GONE
                Toast.makeText(requireContext(),"Hold On",Toast.LENGTH_SHORT).show()

            }else{
                displayProgressBar(false)
                quotelistbinding.btnRetry.visibility = View.VISIBLE
                quotelistbinding.tvError.text = "No Internet Connection!"

            }



        }

    quotelistbinding.fabSaved.setOnClickListener {
        findNavController().navigate(R.id.action_quoteListFrag_to_favoriteFrag)
    }

         adapter = QuoteAdapter(quotelist, this)
        quotelistbinding.listQuoteRecycler.adapter = adapter
        quotelistbinding.listQuoteRecycler.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )

        quotelistbinding.listQuoteRecycler.smoothScrollToPosition(0)



     /*  quotesList.observe(viewLifecycleOwner, Observer {dataState->

            when(dataState){

                is DataState.Loading->{
                            displayProgressBar(true)
                }

                is DataState.Success ->{

                       displayProgressBar(false)

                    adapter.updateListItems(dataState.data as MutableList<QuoteModel>)
                    quotelistbinding.btnRetry.visibility = View.GONE
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

    private fun getData(){
        quoteViewModel._myResponse.observe(viewLifecycleOwner, Observer { dataState ->
            when (dataState) {

                is DataState.Loading -> {
                    displayProgressBar(true)

                }

                is DataState.Success -> {

                    displayProgressBar(false)
                    //val quotesResult = dataState.data
                    adapter.updateListItems(dataState.data as MutableList<QuoteModel>)
                    quotelistbinding.btnRetry.visibility = View.GONE
                }
                is DataState.Failed -> {
                    displayProgressBar(false)
                    displayError(dataState.message.toString())

                }

                is DataState.Exception -> {
                    displayProgressBar(false)
                    displayError("Check internet connection")
                }
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.quote_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.miFav->{
                     Toast.makeText(requireContext(),"Developed with love",Toast.LENGTH_SHORT).show()
            }
        }

        return super.onOptionsItemSelected(item)
    }



    @SuppressLint("SetTextI18n")
    private fun displayError(message: String) {
        quotelistbinding.tvError.text = message

    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        quotelistbinding.progBar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override suspend fun likelistener(quote: QuoteModel, position: Int) {
        localquoteViewModel.addQuote(quote)
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(requireContext(), "Saved", Toast.LENGTH_SHORT).show()
        }
    }

    override suspend fun unlikeListener(quote: QuoteModel, position: Int) {
       // localquoteViewModel.removeQuote(quote)
    }



}