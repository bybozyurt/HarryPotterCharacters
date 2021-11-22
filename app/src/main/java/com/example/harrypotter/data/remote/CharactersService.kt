package com.example.harrypotter.data.remote

import com.example.harrypotter.util.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CharactersService {

//    @Singleton
//    @Provides
//    fun getData(): Call<List<CharactersItem>> {
//        return getRetroInstance().getCharacters()
//
//    }

    fun getRetroInstance(): CharactersApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CharactersApi::class.java)
    }

    

}