package com.example.harrypotter.data.remote

import com.example.harrypotter.data.model.CharactersItem
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Ahmet Bozyurt on 19.11.2021
 */
interface CharactersApi {
    @GET("api/characters")
    fun getCharacters() : Call<List<CharactersItem>>
}