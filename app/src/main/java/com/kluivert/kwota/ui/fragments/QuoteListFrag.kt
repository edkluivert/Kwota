package com.kluivert.kwota.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.view.View.inflate
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
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

    private var snackbar: Snackbar? = null

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

        val myNetwork = CheckNetwork(requireContext())

        setHasOptionsMenu(true)

        myNetwork.isOnline
        if (myNetwork.isOnline){
            quoteViewModel.setQuoteEvents()
            quotelistbinding.btnRetry.visibility = View.GONE
            quotelistbinding.tvError.visibility = View.GONE
        }else{
            displayProgressBar(false)
            quotelistbinding.btnRetry.visibility = View.VISIBLE
            quotelistbinding.tvError.text = "No Internet Connection!"
            snackbar = Snackbar.make(quotelistbinding.root, "You are offline, Please try again", Snackbar.LENGTH_LONG) //Assume "rootLayout" as the root layout of every activity.
            snackbar?.duration = BaseTransientBottomBar.LENGTH_INDEFINITE
            snackbar?.show()
           snackbar?.dismiss()
        }



        quotelistbinding.btnRetry.setOnClickListener {
            displayProgressBar(true)
            quoteViewModel.setQuoteEvents()
            Toast.makeText(requireContext(),"Hold On",Toast.LENGTH_SHORT).show()

        }

    quotelistbinding.fabSaved.setOnClickListener {
        findNavController().navigate(R.id.action_quoteListFrag_to_favoriteFrag)
    }

        val adapter = QuoteAdapter(quotelist, this)
        quotelistbinding.listQuoteRecycler.adapter = adapter
        quotelistbinding.listQuoteRecycler.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )


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

        quotelistbinding.listQuoteRecycler.smoothScrollToPosition(0)



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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.quote_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.miFav->{

            }
        }

        return super.onOptionsItemSelected(item)
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

    override suspend fun likelistener(quote: QuoteModel, position: Int) {
        //observeLocalViewModel()

       val author  = tvAuthor.text.toString()
      val text  = tvQuote.text.toString()


        localquoteViewModel.addQuote(quote)
    }

    override suspend fun unlikeListener(quote: QuoteModel, position: Int) {
        localquoteViewModel.removeQuote(quote)
    }



    /* private fun observeLocalViewModel(){
         localquoteViewModel.fetchError().observe(viewLifecycleOwner,
              Observer<String> { t -> Toast.makeText(requireContext(),t, Toast.LENGTH_LONG).show() })

         localquoteViewModel.fetchInsertedId().observe(viewLifecycleOwner,
              Observer<Long> { t ->
                  if(t != -1L){
                      Toast.makeText(requireContext(),"Saved $t", Toast.LENGTH_LONG).show()

                  }else{
                      Toast.makeText(requireContext(),"Failed",Toast.LENGTH_LONG).show()

                  }

              })
      }*/


}