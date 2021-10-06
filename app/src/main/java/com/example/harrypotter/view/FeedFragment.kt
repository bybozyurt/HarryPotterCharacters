package com.example.harrypotter.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.harrypotter.R
import com.example.harrypotter.ViewModel.FeedViewModel
import com.example.harrypotter.adapter.CharactersAdapter
import com.example.harrypotter.databinding.FragmentFeedBinding
import kotlinx.android.synthetic.main.fragment_feed.*


class FeedFragment : Fragment() {

    private var _binding : FragmentFeedBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FeedViewModel
    private val characterAdapter = CharactersAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFeedBinding.inflate(inflater,container,false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)


        viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        viewModel.refreshData()

        characterListRecylerView.layoutManager = LinearLayoutManager(context)
        characterListRecylerView.adapter = characterAdapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            characterListRecylerView.visibility = View.GONE
            characterError.visibility = View.GONE
            characterLoading.visibility = View.VISIBLE
            viewModel.refreshFromApi()
            swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()


    }

    private fun observeLiveData() {
        viewModel.character.observe(viewLifecycleOwner, Observer { character ->
            character?.let {
                characterListRecylerView.visibility = View.VISIBLE
                characterAdapter.updateCharacterList(character)
            }
        })

        viewModel.characterError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {

                if (it) {
                    characterError.visibility = View.VISIBLE
                } else {
                    characterError.visibility = View.GONE
                }
            }
        })

        viewModel.characterLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (it) {
                    binding.characterLoading.visibility = View.VISIBLE
                    binding.characterListRecylerView.visibility = View.GONE
                    binding.characterError.visibility = View.GONE
                } else {
                    binding.characterLoading.visibility = View.GONE
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val action = FeedFragmentDirections.actionFeedFragmentToFavoriteFragment()
        when(item.itemId){
            R.id.favorite_menu -> view?.let { Navigation.findNavController(it).navigate(action) }
        }
        return true
    }


}