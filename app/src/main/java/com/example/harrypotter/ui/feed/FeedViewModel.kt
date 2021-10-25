package com.example.harrypotter.ui.feed

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.harrypotter.ui.base.BaseViewModel
import com.example.harrypotter.data.model.CharactersItem
import com.example.harrypotter.data.remote.CharactersApi
import com.example.harrypotter.repository.CharactersRepository
import com.example.harrypotter.util.CustomSharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    application: Application,
    private val repository: CharactersRepository,
    private val charactersApi: CharactersApi
) : BaseViewModel(application) {

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
            val characters = repository.getAllCharacters()
            showCharacters(characters)
            feedState.value = FeedViewState.ShowSqliteMessage
        }
    }

    private fun getDataFromAPI() {
        feedState.value = FeedViewState.FeedLoadingViewState(true)

        charactersApi.getCharacters()
            .enqueue(object : Callback<List<CharactersItem>> {
                override fun onResponse(
                    call: Call<List<CharactersItem>>,
                    response: Response<List<CharactersItem>>
                ) {
                    response.body()?.let { storeInSQLite(it)
                    }
                    feedState.value = FeedViewState.ShowApiMessage
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
            repository.deleteAllCharacters()
            val listLong = repository.insertAll(*list.toTypedArray())
            var i = 0
            while (i < list.size){
                list[i].uuid = listLong[i].toInt()
                i++
            }
            showCharacters(list)
        }

        customPreferences.saveTime(System.nanoTime())
    }

    fun updateCharacter(character: CharactersItem) {
        viewModelScope.launch {
            repository.updateCharacter(character)
        }
    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}
