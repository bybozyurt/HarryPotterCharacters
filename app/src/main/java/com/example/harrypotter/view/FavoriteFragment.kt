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
import com.example.harrypotter.ViewModel.FavoriteViewModel
import com.example.harrypotter.adapter.FavoriteAdapter
import com.example.harrypotter.databinding.FragmentFavoriteBinding
import com.example.harrypotter.databinding.FragmentFeedBinding
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.item_favorite.*


class FavoriteFragment : Fragment() {

    private var _binding : FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FavoriteViewModel
    private val favoriteAdapter = FavoriteAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(FavoriteViewModel::class.java)
        viewModel.getFavoriteCharactersFromSQLite()

        recyler_view_favorite.layoutManager = LinearLayoutManager(context)
        recyler_view_favorite.adapter = favoriteAdapter

        observeLiveData()


    }

    private fun observeLiveData(){
        viewModel.characters.observe(viewLifecycleOwner, Observer {
            characters -> characters?.let {
                recyler_view_favorite.visibility = View.VISIBLE
                favoriteAdapter.updateCharacterList(characters)

            }
        })
    }


}