package com.example.harrypotter.data

import com.example.harrypotter.data.local.CharactersDao
import com.example.harrypotter.data.model.CharactersItem
import javax.inject.Inject

/**
 * Created by Ahmet Bozyurt on 19.11.2021
 */
class LocalDataSource @Inject constructor(private val charactersDao: CharactersDao) {

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