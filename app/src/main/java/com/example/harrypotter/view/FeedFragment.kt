package com.example.harrypotter.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.harrypotter.R
import com.example.harrypotter.ViewModel.FeedViewModel
import com.example.harrypotter.adapter.CharactersAdapter
import kotlinx.android.synthetic.main.fragment_feed.*


class FeedFragment : Fragment() {

    private lateinit var viewModel : FeedViewModel
    private val characterAdapter = CharactersAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //ilgili ViewModel ı fragmenta bagladık
        viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        viewModel.refreshData()

        characterListRecylerView.layoutManager = LinearLayoutManager(context)
        characterListRecylerView.adapter = characterAdapter
/*
        buttonFragment.setOnClickListener{
            val action = FeedFragmentDirections.actionFeedFragmentToDetailFragment()
            Navigation.findNavController(it).navigate(action)
        }*/

        swipeRefreshLayout.setOnRefreshListener {
            characterListRecylerView.visibility = View.GONE
            characterError.visibility = View.GONE
            characterLoading.visibility = View.VISIBLE
            viewModel.refreshData()
            swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()



    }

    private fun observeLiveData(){
        viewModel.character.observe(viewLifecycleOwner, Observer {
            character -> character?.let {
                characterListRecylerView.visibility = View.VISIBLE
                characterAdapter.updateCharacterList(character)
        }
        })

        viewModel.characterError.observe(viewLifecycleOwner, Observer {
            error -> error?.let {
            //Boolean true ise
            if(it){
                characterError.visibility = View.VISIBLE
            }
            else{
                characterError.visibility = View.GONE
            }
        }
        })

        viewModel.characterLoading.observe(viewLifecycleOwner, Observer {
            loading -> loading?.let {
                if(it){
                    characterLoading.visibility = View.VISIBLE
                    characterListRecylerView.visibility = View.GONE
                    characterError.visibility = View.GONE
                }
                else{
                    characterLoading.visibility = View.GONE
                }
        }
        })
    }

    
}