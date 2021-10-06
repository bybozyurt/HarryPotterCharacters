package com.example.harrypotter.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.harrypotter.model.CharactersItem

@Dao
interface CharactersDao {

    @Insert
    suspend fun insertAll(vararg characters : CharactersItem) : List<Long>

    @Query("SELECT * FROM charactersitem")
    suspend fun getAllCharacters() : List<CharactersItem>

    @Query("SELECT * FROM charactersitem WHERE uuid = :characterId ")
    suspend fun getCharacter(characterId : Int ) : CharactersItem

    @Query("DELETE FROM charactersitem")
    suspend fun deleteAllCharacters()


    @Insert
    suspend fun addFavoriteCharacter(characters : CharactersItem)





}