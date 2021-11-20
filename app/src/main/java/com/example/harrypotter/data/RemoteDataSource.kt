package com.example.harrypotter.data


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
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Ahmet Bozyurt on 19.11.2021
 */
@AndroidEntryPoint
class RemoteDataSource @Inject constructor(
    private val charactersApi: CharactersApi,
) : CharactersRepository {

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
                    response.body()?.let {list ->
                        emit(Resource.Success(list.map { it.toCharacters() }))
                    }
                        ?: run {
                            emit(Resource.Error("Null ResponseException"))
                        }
                }
            }

        }.catch {
            emit(Resource.Error("Flow ResponseException"))
        }.flowOn(Dispatchers.IO)




}