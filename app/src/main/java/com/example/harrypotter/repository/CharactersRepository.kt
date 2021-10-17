package com.example.harrypotter.repository


import androidx.lifecycle.MutableLiveData
import com.example.harrypotter.data.local.CharactersDao
import com.example.harrypotter.data.model.CharactersItem
import com.example.harrypotter.data.remote.CharactersApi
import com.example.harrypotter.util.Coroutine
import com.example.harrypotter.util.FeedViewState
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@ViewModelScoped
class CharactersRepository @Inject constructor(
    private val charactersApi: CharactersApi,
    private val charactersDao: CharactersDao
) {


    //var feedState: MutableLiveData<FeedViewState> = MutableLiveData<FeedViewState>()

    suspend fun insertAll(vararg character: CharactersItem) : List<Long> {
        return charactersDao.insertAll(*character)
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
//        val call : Call<List<CharactersItem>> = charactersApi.getCharacters()
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
//                feedState.value = FeedViewState.FeedLoadingViewState(false)
//                feedState.value = FeedViewState.FeedErrorViewState(true)
//            }
//        })
//    }
//
//    fun storeInSqlite(list: List<CharactersItem>){
//        launch {
//            deleteAllCharacters()
//            val listLong = insertAll(*list.toTypedArray())
//            var i = 0
//            while (i < list.size){
//                list[i].uuid = listLong[i].toInt()
//                i++
//            }
//            showCharacters(list)
//        }
//
//    }
//
//   fun showCharacters(characterList : List<CharactersItem>) {
//        feedState.value = FeedViewState.FeedCharacterList(characterList)
//        feedState.value = FeedViewState.FeedErrorViewState(false)
//        feedState.value = FeedViewState.FeedLoadingViewState(false)
//
//   }


}