package com.example.harrypotter.detail.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.harrypotter.detail.viewmodel.DetailViewModel
import com.example.harrypotter.databinding.FragmentDetailBinding
import com.example.harrypotter.util.downloadFromApi
import com.example.harrypotter.util.placeHolderProgressBar



class DetailFragment : Fragment() {

    private lateinit var viewModel : DetailViewModel
    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private var characterUuid = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
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
            with(binding){
                characterName.text = character.name
                characterHouse.text = character.house
                characterActor.text = character.actor
                characterAlive.text = character.alive.toString()
                characterAncestry.text = character.ancestry
                characterPatronus.text = character.patronus
            }
                context?.let {
                    binding.imageCharacterDetail.downloadFromApi(character.image, placeHolderProgressBar(it))
                }
            }

        })

    }


}