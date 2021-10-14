package com.example.harrypotter.feed

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.harrypotter.R
import com.example.harrypotter.data.model.CharactersItem
import com.example.harrypotter.databinding.FragmentFeedBinding
import com.example.harrypotter.util.IUpdateCharacter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_feed.*

@AndroidEntryPoint
class FeedFragment : Fragment() {

    private var _binding : FragmentFeedBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FeedViewModel
    private lateinit var characterAdapter : CharactersAdapter


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
        initViewModel()
        observeFeedState()
        recylerAdapter()
        swipeRefresh()
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
        when (item.itemId) {
            R.id.favorite_menu -> view?.let { Navigation.findNavController(it).navigate(action) }
        }
        return true
    }

    fun initViewModel() {
        viewModel = ViewModelProviders.of(requireActivity()).get(FeedViewModel::class.java)
        viewModel.refreshData()
    }

    fun observeFeedState() {
        viewModel.feedState.observe(viewLifecycleOwner) { feedViewState ->
            when (feedViewState) {
                is FeedViewModel.FeedViewState.FeedLoadingViewState -> {
                    feedViewState.stateLoading.let {
                        if (it) {
                            binding.characterLoading.visibility = View.VISIBLE
                            binding.characterListRecylerView.visibility = View.GONE
                            binding.characterError.visibility = View.GONE
                        } else {
                            binding.characterLoading.visibility = View.GONE
                        }
                    }
                }
                is FeedViewModel.FeedViewState.FeedErrorViewState -> {
                    feedViewState.stateError.let {
                        if (it) {
                            characterError.visibility = View.VISIBLE
                        } else {
                            characterError.visibility = View.GONE
                        }
                    }
                }
                is FeedViewModel.FeedViewState.FeedCharacterList -> {
                    feedViewState.characterList.let { character ->
                        characterListRecylerView.visibility = View.VISIBLE
                        characterAdapter.updateCharacterList(character)
                    }
                }
            }
        }
    }

    fun swipeRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            characterListRecylerView.visibility = View.GONE
            characterError.visibility = View.GONE
            characterLoading.visibility = View.VISIBLE
            viewModel.refreshFromApi()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    fun recylerAdapter() {
        characterAdapter = CharactersAdapter(arrayListOf(), updateCharacter = {
            viewModel.updateCharacter(it)
        }

        )

        characterListRecylerView.layoutManager = LinearLayoutManager(context)
        characterListRecylerView.adapter = characterAdapter
    }




}