package com.example.harrypotter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.harrypotter.R
import com.example.harrypotter.databinding.ItemRowBinding
import com.example.harrypotter.model.CharactersItem
import com.example.harrypotter.util.downloadFromApi
import com.example.harrypotter.util.placeHolderProgressBar
import com.example.harrypotter.view.FeedFragmentDirections
import kotlinx.android.synthetic.main.fragment_detail.view.*
import kotlinx.android.synthetic.main.item_row.view.*
import kotlinx.android.synthetic.main.item_row.view.characterHouse
import kotlinx.android.synthetic.main.item_row.view.characterName


class CharactersAdapter(val characterList: ArrayList<CharactersItem>) :
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

                    } else {
                        binding.like.setImageResource(R.drawable.ic_favorite_border)
                        this.flag = false
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