package com.example.harrypotter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.harrypotter.R
import com.example.harrypotter.databinding.ItemFavoriteBinding
import com.example.harrypotter.databinding.ItemRowBinding
import com.example.harrypotter.model.CharactersItem
import com.example.harrypotter.util.downloadFromApi
import com.example.harrypotter.util.placeHolderProgressBar
import kotlinx.android.synthetic.main.item_favorite.view.*

class FavoriteAdapter(val favoriteList : ArrayList<CharactersItem>) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {


    inner class FavoriteViewHolder(var binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemFavoriteBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        with(holder){
            with(favoriteList[position]){
                binding.characterNameFavorite.text = this.name
                binding.imageCharacterFavorite.downloadFromApi(
                    this.image,
                    placeHolderProgressBar(itemView.context)
                )
                binding.delete.visibility = View.GONE

            }
        }
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }

    fun updateCharacterList(newCharacterList : List<CharactersItem>){
        favoriteList.clear()
        favoriteList.addAll(newCharacterList)
        notifyDataSetChanged()

    }
}