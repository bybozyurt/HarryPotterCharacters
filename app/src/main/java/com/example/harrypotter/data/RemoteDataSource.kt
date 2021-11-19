package com.example.harrypotter.data


import com.example.harrypotter.data.model.CharactersItem
import com.example.harrypotter.data.remote.CharactersApi
import retrofit2.Call
import javax.inject.Inject

/**
 * Created by Ahmet Bozyurt on 19.11.2021
 */
class RemoteDataSource @Inject constructor(private val charactersApi: CharactersApi) {

    fun getCharacters(): Call<List<CharactersItem>> {
        return charactersApi.getCharacters()
    }

//    override suspend fun getCharacterList(): Flow<Resource<List<Characters>>> = flow<Resource<List<Characters>>>{
//       val response = charactersApi.getCharacters()
//
//    }
}