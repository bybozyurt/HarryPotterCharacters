package com.example.harrypotter.service

import androidx.room.*
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

    @Update
    suspend fun updateCharacter(character : CharactersItem)

//    @Query("SELECT * FROM charactersitem WHERE uuid = 1")
//    suspend fun getFavoriteCharacters() : List<CharactersItem>

//    @Query("SELECT EXISTS (SELECT 1 FROM charactersitem WHERE uuid= :id)")
//    suspend fun isFavorite(id : Int)





}