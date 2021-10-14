package com.example.harrypotter.di

import com.example.harrypotter.data.remote.CharactersApi
import com.example.harrypotter.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CharactersService {

//    @Singleton
//    @Provides
//    fun getData(): Call<List<CharactersItem>> {
//        return getRetroInstance().getCharacters()
//
//    }


    @Singleton
    @Provides
    fun getRetroInstance(): CharactersApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CharactersApi::class.java)
    }

    

}