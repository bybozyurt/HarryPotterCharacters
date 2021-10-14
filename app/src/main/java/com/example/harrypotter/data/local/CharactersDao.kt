package com.example.harrypotter.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.harrypotter.data.model.CharactersItem

@Dao
interface CharactersDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg characters : CharactersItem) : List<Long>

    @Query("SELECT * FROM charactersitem")
    suspend fun getAllCharacters() : List<CharactersItem>

    @Query("SELECT * FROM charactersitem WHERE uuid = :characterId ")
    suspend fun getCharacter(characterId : Int ) : CharactersItem

    @Query("DELETE FROM charactersitem")
    suspend fun deleteAllCharacters()

    @Update
    suspend fun updateCharacter(character : CharactersItem)

    @Query("SELECT * FROM charactersitem WHERE flag = 1")
    suspend fun getFavoriteCharacters() : List<CharactersItem>





}