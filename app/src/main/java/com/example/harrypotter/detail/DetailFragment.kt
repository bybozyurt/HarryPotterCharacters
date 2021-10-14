package com.example.harrypotter.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.harrypotter.R
import com.example.harrypotter.databinding.FragmentDetailBinding
import com.example.harrypotter.util.downloadFromApi
import com.example.harrypotter.util.placeHolderProgressBar


class DetailFragment() : Fragment() {

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
        getArgument()
        initViewModel()
        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.characterLiveData.observe(viewLifecycleOwner, Observer {
            character -> character.let {
            with(binding){
                characterName.text = character.name
                characterHouse.text = character.house
                characterActor.text = character.actor
                characterAncestry.text = character.ancestry
                characterPatronus.text = character.patronus
            }
                context?.let {
                    binding.imageCharacterDetail.downloadFromApi(character.image, placeHolderProgressBar(it))
                }
            }

            if (character.flag){
                binding.detailLike.setImageResource(R.drawable.ic_favorite)
            }
            else{
                binding.detailLike.setImageResource(R.drawable.ic_favorite_border)
            }

            binding.detailLike.setOnClickListener {
                if (!character.flag) {
                    binding.detailLike.setImageResource(R.drawable.ic_favorite)
                    character.flag = true
                    viewModel.updateCharacter(character)
                } else {
                    binding.detailLike.setImageResource(R.drawable.ic_favorite_border)
                    character.flag = false
                    viewModel.updateCharacter(character)
                }
            }
            binding.imageCharacterDetail.setOnClickListener {
                val action = DetailFragmentDirections.actionDetailFragmentToFeedFragment2()
                Navigation.findNavController(it).navigate(action)
            }
        })
    }

    fun initViewModel(){
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.getDataFromRoom(characterUuid)
    }

    fun getArgument(){
        arguments?.let {
            characterUuid = DetailFragmentArgs.fromBundle(it).characterUuid
        }
    }


}