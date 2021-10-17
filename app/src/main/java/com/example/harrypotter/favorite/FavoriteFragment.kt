package com.example.harrypotter.favorite

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.harrypotter.R
import com.example.harrypotter.data.model.CharactersItem
import com.example.harrypotter.databinding.FragmentFavoriteBinding
import com.example.harrypotter.util.IUpdateCharacter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_favorite.*

@AndroidEntryPoint
class FavoriteFragment : Fragment(), IUpdateCharacter {

    private var _binding : FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoriteViewModel by viewModels()
    private val favoriteAdapter = FavoriteAdapter(arrayListOf(),this)


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
        //setHasOptionsMenu(true)
        initViewModel()
        recylerAdapter()
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

    fun initViewModel(){
        //viewModel = ViewModelProviders.of(this).get(FavoriteViewModel::class.java)
        viewModel.getFavoriteCharactersFromSQLite()
    }

    fun recylerAdapter(){
        recyler_view_favorite.layoutManager = LinearLayoutManager(context)
        recyler_view_favorite.adapter = favoriteAdapter
    }

    override fun updateCharacter(character: CharactersItem) {
        viewModel.updateCharacter(character)
    }


//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.favorite_menu,menu)
//        super.onCreateOptionsMenu(menu, inflater)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val action = FavoriteFragmentDirections.actionFavoriteFragmentToFeedFragment()
//        when(item.itemId){
//            R.id.getBack -> view?.let { Navigation.findNavController(it).navigate(action) }
//
//            else ->{super.onOptionsItemSelected(item)}
//        }
//
//        return true
//    }


}