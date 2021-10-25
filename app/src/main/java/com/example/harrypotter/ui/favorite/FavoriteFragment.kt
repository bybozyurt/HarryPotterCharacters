package com.example.harrypotter.ui.favorite

import android.view.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.harrypotter.R
import com.example.harrypotter.adapter.FavoriteAdapter
import com.example.harrypotter.data.model.CharactersItem
import com.example.harrypotter.databinding.FragmentFavoriteBinding
import com.example.harrypotter.adapter.AdapterInterface
import com.example.harrypotter.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_favorite.*

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(), AdapterInterface {


    private val viewModel: FavoriteViewModel by viewModels()
    private val favoriteAdapter = FavoriteAdapter(arrayListOf(),this)


    private fun observeLiveData(){
        viewModel.characters.observe(viewLifecycleOwner, Observer {
            characters -> characters?.let {
                recyler_view_favorite.visibility = View.VISIBLE
                favoriteAdapter.updateCharacterList(characters)
            }
        })
    }

    fun initViewModel(){
        viewModel.getFavoriteCharactersFromSQLite()
    }

    fun recylerAdapter(){
        recyler_view_favorite.layoutManager = LinearLayoutManager(context)
        recyler_view_favorite.adapter = favoriteAdapter
    }

    override fun updateCharacter(character: CharactersItem) {
        viewModel.updateCharacter(character)
    }

    override val viewId: Int
        get() = R.layout.fragment_favorite

    override fun onCreateFinished() {
        initViewModel()
        recylerAdapter()
        observeLiveData()
    }

    override fun initListeners() {

    }



}