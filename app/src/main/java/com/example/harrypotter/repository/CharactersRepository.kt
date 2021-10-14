package com.example.harrypotter.repository

import com.example.harrypotter.data.local.CharactersDao
import com.example.harrypotter.data.model.CharactersItem
import com.example.harrypotter.data.remote.CharactersApi
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.CoroutineScope
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@ActivityScoped
class CharactersRepository @Inject constructor(
    private val api: CharactersApi,
    private val charactersDao: CharactersDao
){

    suspend fun insertAll(vararg character: CharactersItem) : List<Long> {
        return charactersDao.insertAll()
    }

    suspend fun getAllCharacters(): List<CharactersItem> {
        return charactersDao.getAllCharacters()
    }

    suspend fun getCharacter(characterId: Int): CharactersItem {
        return charactersDao.getCharacter(characterId)
    }

    suspend fun deleteAllCharacters() {
        charactersDao.deleteAllCharacters()
    }

    suspend fun updateCharacter(character: CharactersItem) {
        charactersDao.updateCharacter(character)
    }

    suspend fun getFavoriteCharacters(): List<CharactersItem> {
        return charactersDao.getFavoriteCharacters()
    }

//    fun makeApiCall(){
//        val call : Call<List<CharactersItem>> = api.getCharacters()
//        call?.enqueue(object : Callback<List<CharactersItem>>{
//            override fun onResponse(
//                call: Call<List<CharactersItem>>,
//                response: Response<List<CharactersItem>>
//            ) {
//                response.body()?.let {
//                    storeInSqlite(it)
//                }
//            }
//
//            override fun onFailure(call: Call<List<CharactersItem>>, t: Throwable) {
//                //sealed
//            }
//        })
//    }
//
//    fun storeInSqlite(list: List<CharactersItem>){
//        deleteAllCharacters()
//    }
}