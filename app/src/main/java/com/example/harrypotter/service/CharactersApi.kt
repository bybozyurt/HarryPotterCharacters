package com.example.harrypotter.service

import com.example.harrypotter.model.CharactersItem
import io.reactivex.Single
import retrofit2.http.GET

interface CharactersApi {

    @GET("api/characters")
    fun getCharacters() : Single<List<CharactersItem>>

}