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
            with(DetailFragmentArgs.fromBundle(it)){
                val name = username
                val house = house
                val actor = actor
                val patronus = patronus
                val alive = alive
                val image = image

                characterName.text = name
                characterHouse.text = house
                characterActor.text = actor
                characterAlive.text = alive.toString()
                characterPatronus.text = patronus
                context?.let {
                    imageCharacterDetail.downloadFromApi(image, placeHolderProgressBar(it))
                }

            }



        }

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        //viewModel.getDataS(characterUuid)



        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.characterLiveData.observe(viewLifecycleOwner, Observer {
            character -> character.let {
                characterName.text = character.name
                characterHouse.text = character.house
                characterActor.text = character.actor



        }

        })

    }


}