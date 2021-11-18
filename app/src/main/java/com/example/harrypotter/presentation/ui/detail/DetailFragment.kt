package com.example.harrypotter.presentation.ui.detail

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.harrypotter.R
import com.example.harrypotter.databinding.FragmentDetailBinding
import com.example.harrypotter.presentation.ui.base.BaseFragment
import com.example.harrypotter.util.downloadFromApi
import com.example.harrypotter.util.placeHolderProgressBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment() : BaseFragment<FragmentDetailBinding>() {

    private val viewModel : DetailViewModel by viewModels()

    override val viewId: Int
        get() = R.layout.fragment_detail

    var characterUuid = 0

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
        viewModel.getDataFromRoom(characterUuid)
    }

    fun getArgument(){
        arguments?.let {
            characterUuid = DetailFragmentArgs.fromBundle(it).characterUuid
        }
    }

    override fun onCreateFinished() {
        getArgument()
        initViewModel()
    }

    override fun initListeners() {
        observeLiveData()
    }


}