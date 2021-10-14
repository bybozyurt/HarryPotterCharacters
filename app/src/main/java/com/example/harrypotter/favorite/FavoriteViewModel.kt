package com.example.harrypotter.favorite

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.harrypotter.ViewModel.BaseViewModel
import com.example.harrypotter.data.model.CharactersItem
import com.example.harrypotter.data.local.CharacterDatabase
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : BaseViewModel(application) {

    val characters = MutableLiveData<List<CharactersItem>>()

    fun getFavoriteCharactersFromSQLite(){
        launch {
            val favoriteCharacters = CharacterDatabase(getApplication()).characterDao().getFavoriteCharacters()
            showCharacters(favoriteCharacters)
        }
    }

    private fun showCharacters(favoritelist : List<CharactersItem>){
        characters.value = favoritelist
    }

    fun updateCharacter(character : CharactersItem){
        viewModelScope.launch {
            val dao = CharacterDatabase(getApplication()).characterDao()
            dao.updateCharacter(character)
        }
    }





}