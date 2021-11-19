package com.example.harrypotter.data.repository


import com.example.harrypotter.data.LocalDataSource
import com.example.harrypotter.data.RemoteDataSource
import com.example.harrypotter.data.local.CharactersDao
import com.example.harrypotter.data.model.CharactersItem
import com.example.harrypotter.data.remote.CharactersApi
import com.example.harrypotter.domain.model.Characters
import com.example.harrypotter.domain.repository.CharactersRepository
import com.example.harrypotter.util.extesions.Resource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject


@ViewModelScoped
class CharactersRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {

//    override suspend fun getCharacterList(): Flow<Resource<List<Characters>>> {
//        //return remoteDataSource.getCharacterList()
//    }

    fun getCharacters(): Call<List<CharactersItem>> {
        return remoteDataSource.getCharacters()
    }

    //room
    suspend fun insertAll(vararg character: CharactersItem) : List<Long>{
        return localDataSource.insertAll(*character)
    }

    suspend fun getAllCharacters(): List<CharactersItem>{
        return localDataSource.getAllCharacters()
    }

    suspend fun getCharacter(characterId: Int): CharactersItem{
        return localDataSource.getCharacter(characterId)
    }

    suspend fun deleteAllCharacters(){
        return localDataSource.deleteAllCharacters()
    }

    suspend fun updateCharacter(character: CharactersItem){
        return localDataSource.updateCharacter(character)
    }

    suspend fun getFavoriteCharacters(): List<CharactersItem>{
        return localDataSource.getFavoriteCharacters()
    }




}