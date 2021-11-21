package com.example.harrypotter.data


import androidx.lifecycle.viewModelScope
import com.example.harrypotter.data.model.CharactersItem
import com.example.harrypotter.data.model.toCharacters
import com.example.harrypotter.data.remote.CharactersApi
import com.example.harrypotter.data.repository.CharactersRepositoryImpl
import com.example.harrypotter.domain.model.Characters
import com.example.harrypotter.domain.repository.CharactersRepository
import com.example.harrypotter.presentation.ui.feed.FeedViewState
import com.example.harrypotter.util.extesions.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Ahmet Bozyurt on 19.11.2021
 */
@AndroidEntryPoint
class RemoteDataSource @Inject constructor(
    private val charactersApi: CharactersApi,
    private val repository: CharactersRepositoryImpl
) : CharactersRepository {

//    private val _state: MutableStateFlow<FeedViewState> = MutableStateFlow(FeedViewState.Loading)
//    val state: StateFlow<FeedViewState> = _state

    fun getCharacters(): Response<List<CharactersItem>> {
        return charactersApi.getCharacters()
    }

    //get characters from api
    override fun getCharacterList(): Flow<Resource<List<Characters>>> =
        flow<Resource<List<Characters>>> {
            val response = charactersApi.getCharacters()
            when (response.isSuccessful) {
                false -> {
                    emit(Resource.Error("Fail ResponseException"))
                }
                true -> {
                    response.body()?.let {
                        //storeInSQLite(it)
                        emit(Resource.Success(it.map { it.toCharacters() }))
                        //_state.emit(FeedViewState.ShowApiMessage)
                    }

                        ?: run {
                            emit(Resource.Error("Null ResponseException"))
                        }
                }
            }

        }.catch {
            emit(Resource.Error("Flow ResponseException"))
        }.flowOn(Dispatchers.IO)


//    suspend fun showCharacters(characterList: List<CharactersItem>) {
//        _state.emit(FeedViewState.FeedCharacterList(characterList))
//        //_state.emit(FeedViewState.FeedErrorViewState(false))
//        //_state.emit(FeedViewState.FeedLoadingViewState(false))
//
//    }

//    suspend fun storeInSQLite(list: List<CharactersItem>) {
//        repository.deleteAllCharacters()
//        val listLong = repository.insertAll(*list.toTypedArray())
//        var i = 0
//        while (i < list.size) {
//            list[i].uuid = listLong[i].toInt()
//            i++
//        }
//        showCharacters(list)
//
//    }

//    suspend fun getDataFromSQLite() {
//        _state.emit(FeedViewState.FeedLoadingViewState(true))
//        val characters = repository.getAllCharacters()
//        showCharacters(characters)
//        _state.emit(FeedViewState.ShowSqliteMessage)
//    }

    suspend fun updateCharacter(character: CharactersItem) {
        repository.updateCharacter(character)
    }


}