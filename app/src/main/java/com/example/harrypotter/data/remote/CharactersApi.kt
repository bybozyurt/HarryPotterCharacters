package com.example.harrypotter.data.remote

import com.example.harrypotter.data.model.CharactersItem
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface CharactersApi {

    @GET("api/characters")
    fun getCharacters() : Call<List<CharactersItem>>

}