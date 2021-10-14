package com.example.harrypotter.feed

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.harrypotter.ViewModel.BaseViewModel
import com.example.harrypotter.data.local.CharacterDatabase
import com.example.harrypotter.data.model.CharactersItem
import com.example.harrypotter.network.CharactersService
import com.example.harrypotter.util.CustomSharedPreferences
import com.example.harrypotter.util.FeedViewState
import com.example.harrypotter.util.IUpdateCharacter
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedViewModel(
    application: Application,
) : BaseViewModel(application),IUpdateCharacter {

    private val charactersService = CharactersService()
    private val disposable = CompositeDisposable()
    private var customPreferences = CustomSharedPreferences(getApplication())

    var feedState: MutableLiveData<FeedViewState> = MutableLiveData<FeedViewState>()

    //nano second
    private var refreshTime = 10 * 60 * 1000 * 1000 * 1000L

    fun refreshData() {
        val updateTime = customPreferences.getTime()
        if(updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime){
            getDataFromSQLite()
        } else{
            getDataFromAPI()
        }
    }

    fun refreshFromApi(){
        getDataFromAPI()
    }

    private fun getDataFromSQLite(){
        feedState.value = FeedViewState.FeedLoadingViewState(true)
        viewModelScope.launch {
            val characters = CharacterDatabase(getApplication()).characterDao().getAllCharacters()
            showCharacters(characters)
            Toast.makeText(getApplication(),"Characters from SQLite",Toast.LENGTH_LONG).show()
        }
    }

    private fun getDataFromAPI() {
        feedState.value = FeedViewState.FeedLoadingViewState(true)

        charactersService.getRetroInstance().getCharacters()
            .enqueue(object : Callback<List<CharactersItem>> {
                override fun onResponse(
                    call: Call<List<CharactersItem>>,
                    response: Response<List<CharactersItem>>
                ) {
                    response.body()?.let { storeInSQLite(it) }
                    Toast.makeText(getApplication(), "Characters from API", Toast.LENGTH_LONG)
                        .show()
                }

                override fun onFailure(call: Call<List<CharactersItem>>, t: Throwable) {
                    feedState.value = FeedViewState.FeedLoadingViewState(false)
                    feedState.value = FeedViewState.FeedErrorViewState(true)
                    t.printStackTrace()

                }

            })


    }

    private fun showCharacters(characterList : List<CharactersItem>) {
        feedState.value = FeedViewState.FeedCharacterList(characterList)
        feedState.value = FeedViewState.FeedErrorViewState(false)
        feedState.value = FeedViewState.FeedLoadingViewState(false)

    }

    private fun storeInSQLite(list : List<CharactersItem>){
        viewModelScope.launch {
            val dao = CharacterDatabase(getApplication()).characterDao()
            dao.deleteAllCharacters()
            val listLong = dao.insertAll(*list.toTypedArray())
            var i = 0
            while (i < list.size){
                list[i].uuid = listLong[i].toInt()
                i++
            }
            showCharacters(list)
        }

        customPreferences.saveTime(System.nanoTime())
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }



    override fun updateCharacter(character: CharactersItem) {
        viewModelScope.launch {
            val dao = CharacterDatabase(getApplication()).characterDao()
            dao.updateCharacter(character)
        }
    }

//    sealed class FeedViewState {
//        data class FeedErrorViewState(val stateError: Boolean) : FeedViewState()
//        data class FeedLoadingViewState(val stateLoading: Boolean) : FeedViewState()
//        data class FeedCharacterList(val characterList: List<CharactersItem>) : FeedViewState()
//    }


}



