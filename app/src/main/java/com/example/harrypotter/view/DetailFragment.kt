package com.example.harrypotter.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.harrypotter.R
import com.example.harrypotter.ViewModel.DetailViewModel
import com.example.harrypotter.util.downloadFromApi
import com.example.harrypotter.util.placeHolderProgressBar
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.item_row.*
import kotlinx.android.synthetic.main.item_row.characterHouse
import kotlinx.android.synthetic.main.item_row.characterName


class DetailFragment : Fragment() {

    private lateinit var viewModel : DetailViewModel
    private var characterUuid = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            characterUuid = DetailFragmentArgs.fromBundle(it).characterUuid
        }

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.getDataFromRoom(characterUuid)
        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.characterLiveData.observe(viewLifecycleOwner, Observer {
            character -> character.let {
                characterName.text = character.name
                characterHouse.text = character.house
                characterActor.text = character.actor
                characterAlive.text = character.alive.toString()
                characterAncestry.text = character.ancestry
                characterPatronus.text = character.patronus
                context?.let {
                    imageCharacterDetail.downloadFromApi(character.image, placeHolderProgressBar(it))
                }
            }

        })

    }


}