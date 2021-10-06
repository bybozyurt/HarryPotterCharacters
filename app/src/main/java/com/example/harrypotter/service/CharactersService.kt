package com.example.harrypotter.service

import com.example.harrypotter.model.CharactersItem
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CharactersService {

    private val BASE_URL = "https://hp-api.herokuapp.com/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CharactersApi::class.java)

    fun getData() : Call<List<CharactersItem>>{
        return api.getCharacters()
    }
}