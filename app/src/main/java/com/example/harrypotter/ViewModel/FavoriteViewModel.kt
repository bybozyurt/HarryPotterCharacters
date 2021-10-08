package com.example.harrypotter.ViewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.harrypotter.model.CharactersItem
import com.example.harrypotter.service.CharacterDatabase
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





}