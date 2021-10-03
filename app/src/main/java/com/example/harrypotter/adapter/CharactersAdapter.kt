package com.example.harrypotter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.harrypotter.R
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

    class CharactersViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_row, parent, false)
        return CharactersViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.view.characterName.text = characterList[position].name
        holder.view.characterHouse.text = characterList[position].house


        holder.view.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToDetailFragment(
                characterUuid = characterList[position].uuid
            )
            Navigation.findNavController(it).navigate(action)

        }
        if (characterList[position].flag) {
            holder.view.like.setImageResource(R.drawable.ic_favorite)

        } else {
            holder.view.like.setImageResource(R.drawable.ic_favorite_border)

        }

        holder.view.like.setOnClickListener {

            if (!characterList[position].flag) {

                holder.view.like.setImageResource(R.drawable.ic_favorite)
                characterList[position].flag = true

            } else {
                holder.view.like.setImageResource(R.drawable.ic_favorite_border)
                characterList[position].flag = false
            }

        }
        holder.view.imageCharacterFeed.downloadFromApi(
            characterList[position].image,
            placeHolderProgressBar(holder.view.context)
        )

//        Glide.with(holder.view.context)
//            .load(characterList[position].image)
//            .into(holder.view.imageCharacterFeed)


    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    fun updateCharacterList(newCharacterList: List<CharactersItem>) {
        characterList.clear()
        characterList.addAll(newCharacterList)
        //adapter yeniler
        notifyDataSetChanged()

    }
}