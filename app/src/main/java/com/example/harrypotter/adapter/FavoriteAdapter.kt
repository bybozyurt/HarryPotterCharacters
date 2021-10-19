package com.example.harrypotter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.harrypotter.R
import com.example.harrypotter.data.model.CharactersItem
import com.example.harrypotter.databinding.ItemFavoriteBinding
import com.example.harrypotter.ui.favorite.FavoriteFragment
import com.example.harrypotter.ui.favorite.FavoriteFragmentDirections
import com.example.harrypotter.util.downloadFromApi
import com.example.harrypotter.util.placeHolderProgressBar

class FavoriteAdapter(
    val favoriteList: ArrayList<CharactersItem>,
    val favoriteFragment: FavoriteFragment
) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {


    inner class FavoriteViewHolder(var binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemFavoriteBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        with(holder) {
            with(favoriteList[position]) {
                binding.characterNameFavorite.text = this.name
                binding.imageCharacterFavorite.downloadFromApi(
                    this.image,
                    placeHolderProgressBar(itemView.context)
                )

                itemView.setOnClickListener {
                    val action = FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(
                        characterUuid = this.uuid
                    )
                    Navigation.findNavController(it).navigate(action)

                }

                if (this.flag) {
                    binding.favoriteLike.setImageResource(R.drawable.ic_favorite)

                } else {
                    binding.favoriteLike.setImageResource(R.drawable.ic_favorite_border)

                }
                binding.favoriteLike.setOnClickListener {
                    if (!this.flag) {
                        binding.favoriteLike.setImageResource(R.drawable.ic_favorite)
                        this.flag = true
                        favoriteFragment.updateCharacter(this)
                        removeCharacter(this)


                    } else {
                        binding.favoriteLike.setImageResource(R.drawable.ic_favorite_border)
                        this.flag = false
                        favoriteFragment.updateCharacter(this)
                        removeCharacter(this)

                    }
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }

    fun updateCharacterList(newCharacterList: List<CharactersItem>) {
        favoriteList.clear()
        favoriteList.addAll(newCharacterList)
        notifyDataSetChanged()

    }

    fun removeCharacter(position: CharactersItem) {
        favoriteList.remove(position)
        notifyDataSetChanged()
    }
}