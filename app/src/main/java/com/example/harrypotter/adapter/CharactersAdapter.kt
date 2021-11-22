package com.example.harrypotter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.harrypotter.R
import com.example.harrypotter.data.model.CharactersItem
import com.example.harrypotter.databinding.ItemRowBinding
import com.example.harrypotter.presentation.ui.feed.FeedFragment
import com.example.harrypotter.presentation.ui.feed.FeedFragmentDirections
import com.example.harrypotter.util.downloadFromApi
import com.example.harrypotter.util.placeHolderProgressBar

class CharactersAdapter(
    val characterList: ArrayList<CharactersItem>,
    val feedFragment: FeedFragment
) :
    RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder>() {

    inner class CharactersViewHolder(val binding: ItemRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val binding = ItemRowBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return CharactersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {

        with(holder) {
            with(characterList[position]) {
                binding.characterName.text = this.name
                binding.characterHouse.text = this.house

                itemView.setOnClickListener {
                    val action = FeedFragmentDirections.actionFeedFragmentToDetailFragment(
                        characterUuid = this.uuid
                    )
                    Navigation.findNavController(it).navigate(action)

                }
                if (this.flag) {
                    binding.like.setImageResource(R.drawable.ic_favorite)

                } else {
                    binding.like.setImageResource(R.drawable.ic_favorite_border)

                }
                binding.like.setOnClickListener {
                    if (!this.flag) {
                        binding.like.setImageResource(R.drawable.ic_favorite)
                        this.flag = true
                        //updateCharacter(this)
                        feedFragment.updateCharacter(this)


                    } else {
                        binding.like.setImageResource(R.drawable.ic_favorite_border)
                        this.flag = false
                        feedFragment.updateCharacter(this)

                    }
                }
                binding.imageCharacterFeed.downloadFromApi(
                    this.image,
                    placeHolderProgressBar(itemView.context)
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    fun updateCharacterList(newCharacterList: List<CharactersItem>) {
        characterList.clear()
        characterList.addAll(newCharacterList)
        notifyDataSetChanged()

    }



}