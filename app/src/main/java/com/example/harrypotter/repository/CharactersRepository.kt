package com.example.harrypotter.repository


import com.example.harrypotter.data.local.CharactersDao
import com.example.harrypotter.data.model.CharactersItem
import com.example.harrypotter.data.remote.CharactersApi
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject


@ViewModelScoped
class CharactersRepository @Inject constructor(
    private val charactersDao: CharactersDao
) {

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




}