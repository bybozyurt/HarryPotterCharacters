package com.example.harrypotter.ViewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.harrypotter.model.CharactersItem
import com.example.harrypotter.service.CharacterDatabase
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : BaseViewModel(application) {

    val favoriteCharacter = MutableLiveData<List<CharactersItem>>()

    private fun getDataFromSQLite(){
        launch {
            //dao
            //showCharacters() method

        }
    }

    private fun showCharacters(favoritelist : List<CharactersItem>){
        favoriteCharacter.value = favoritelist
    }





}