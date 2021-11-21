package com.example.harrypotter.presentation.ui.feed

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.harrypotter.R
import com.example.harrypotter.adapter.AdapterInterface
import com.example.harrypotter.adapter.CharactersAdapter
import com.example.harrypotter.data.RemoteDataSource
import com.example.harrypotter.data.model.CharactersItem
import com.example.harrypotter.databinding.FragmentFeedBinding
import com.example.harrypotter.presentation.ui.base.BaseFragment
import com.example.harrypotter.util.extesions.*
import com.example.harrypotter.util.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_feed.*
import javax.inject.Inject

@AndroidEntryPoint
class FeedFragment @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    BaseFragment<FragmentFeedBinding>(), AdapterInterface {


    private val viewModel: FeedViewModel by viewModels()
    private lateinit var characterAdapter: CharactersAdapter

    val state = viewModel.state.value



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val action = FeedFragmentDirections.actionFeedFragmentToFavoriteFragment()
        when (item.itemId) {
            R.id.favorite_menu -> view?.let { Navigation.findNavController(it).navigate(action) }
        }
        return true
    }

    fun asd(){
        if (state.)
    }

//    fun observeFeedState() {
//        viewModel.feedState.observe(viewLifecycleOwner) { feedViewState ->
//            when (feedViewState) {
//                is FeedViewState.FeedLoadingViewState -> {
//                    feedViewState.stateLoading.let {
//                        with(binding) {
//                            if (it) {
//                                characterLoading.show()
//                                characterListRecylerView.hide()
//                                characterError.hide()
//                            } else {
//                                characterLoading.hide()
//                            }
//                        }
//
//                    }
//                }
//                is FeedViewState.FeedErrorViewState -> {
//                    feedViewState.stateError.let {
//                        if (it) {
//                            characterError.show()
//                        } else {
//                            characterError.hide()
//                        }
//                    }
//                }
//                is FeedViewState.FeedCharacterList -> {
//                    feedViewState.characterList.let { character ->
//                        characterListRecylerView.show()
//                        characterAdapter.updateCharacterList(character)
//                    }
//                }
//                is FeedViewState.ShowApiMessage -> {
//                    toast(requireContext(), "Characters from API")
//                }
//                is FeedViewState.ShowSqliteMessage -> {
//                    toast(requireContext(), "Characters from SQLite")
//                }
//
//            }
//        }
//    }

//    fun swipeRefresh() {
//        binding.swipeRefreshLayout.setOnRefreshListener {
//            characterListRecylerView.hide()
//            characterError.hide()
//            characterLoading.show()
//            viewModel.refreshFromApi()
//            swipeRefreshLayout.isRefreshing = false
//        }
//    }

    fun recylerAdapter() {
        characterAdapter = CharactersAdapter(arrayListOf(), this)
        characterListRecylerView.layoutManager = LinearLayoutManager(context)
        characterListRecylerView.adapter = characterAdapter
    }

    override fun updateCharacter(character: CharactersItem) {
        //viewModel.updateCharacter(character)
    }

    override val viewId: Int
        get() = R.layout.fragment_feed

    override fun onCreateFinished() {
        setHasOptionsMenu(true)
        viewModel.refreshData()
        recylerAdapter()
        //observeFeedState()
    }

    override fun initListeners() {
        //swipeRefresh()
    }


}