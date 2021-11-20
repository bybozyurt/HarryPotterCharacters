package com.example.harrypotter.presentation.ui.feed

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.harrypotter.data.model.CharactersItem
import com.example.harrypotter.data.repository.CharactersRepositoryImpl
import com.example.harrypotter.domain.use_case.get_characters.GetCharactersUseCase
import com.example.harrypotter.presentation.ui.base.BaseViewModel
import com.example.harrypotter.util.CustomSharedPreferences
import com.example.harrypotter.util.extesions.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    application: Application,
    private val repository: CharactersRepositoryImpl,
    private val useCase: GetCharactersUseCase
) : BaseViewModel(application) {


    private val _state : MutableStateFlow<FeedViewState> = MutableStateFlow(FeedViewState.Loading)
    val state: StateFlow<FeedViewState> = _state

    private val disposable = CompositeDisposable()
    private var customPreferences = CustomSharedPreferences(getApplication())

    var feedState: MutableLiveData<FeedViewState> = MutableLiveData<FeedViewState>()

    //nano second
    private var refreshTime = 10 * 60 * 1000 * 1000 * 1000L

    fun refreshData() {
        val updateTime = customPreferences.getTime()
        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime) {
            //getDataFromSQLite()
        } else {
            //getDataFromAPI()
        }
    }

    fun refreshFromApi() {
        //getDataFromAPI()
    }

    private fun getDataFromSQLite() {
        feedState.value = FeedViewState.FeedLoadingViewState(true)
        viewModelScope.launch {
            val characters = repository.getAllCharacters()
            showCharacters(characters)
            _state.emit(FeedViewState.ShowSqliteMessage)
            feedState.value = FeedViewState.ShowSqliteMessage
        }
    }

    private fun getDataFromAPI() {
        feedState.value = FeedViewState.FeedLoadingViewState(true)
        repository.getCharacters()
            .enqueue(object : Callback<List<CharactersItem>> {
                override fun onResponse(
                    call: Call<List<CharactersItem>>,
                    response: Response<List<CharactersItem>>
                ) {
                    response.body()?.let {
                        storeInSQLite(it)
                    }
                    feedState.value = FeedViewState.ShowApiMessage
                }

                override fun onFailure(call: Call<List<CharactersItem>>, t: Throwable) {
                    feedState.value = FeedViewState.FeedLoadingViewState(false)
                    feedState.value = FeedViewState.FeedErrorViewState(true)
                    t.printStackTrace()
                }

            })

        viewModelScope.launch {
            _state.emit(FeedViewState.FeedLoadingViewState(true))
            useCase.getCharacterList().collect {
                when(it){
                    is Resource.Error -> {
                        _state.emit(FeedViewState.FeedErrorViewState(false))
                    }
                    is Resource.Loading -> {
                        _state.emit(FeedViewState.Loading)
                    }
//                    is Resource.Success -> {
//                        _state.emit(FeedViewState.FeedCharacterList())
//                    }
                    is Resource.Success -> {
                        _state.emit(FeedViewState.ShowApiMessage)
                    }
                }
            }

        }



    }

    private suspend fun showCharacters(characterList: List<CharactersItem>) {
        _state.emit(FeedViewState.FeedCharacterList(characterList))
        _state.emit(FeedViewState.FeedErrorViewState(false))
        _state.emit(FeedViewState.FeedLoadingViewState(false))

//        feedState.value = FeedViewState.FeedCharacterList(characterList)
//        feedState.value = FeedViewState.FeedErrorViewState(false)
//        feedState.value = FeedViewState.FeedLoadingViewState(false)

    }

    private fun storeInSQLite(list: List<CharactersItem>) {
        viewModelScope.launch {
            repository.deleteAllCharacters()
            val listLong = repository.insertAll(*list.toTypedArray())
            var i = 0
            while (i < list.size) {
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

