package com.example.harrypotter.presentation.ui.favorite

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.harrypotter.presentation.ui.base.BaseViewModel
import com.example.harrypotter.data.model.CharactersItem
import com.example.harrypotter.data.repository.CharactersRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(application: Application, private val repository: CharactersRepositoryImpl) : BaseViewModel(application) {

    val characters = MutableLiveData<List<CharactersItem>>()

    fun getFavoriteCharactersFromSQLite(){
        viewModelScope.launch {
            val favoriteCharacters = repository.getFavoriteCharacters()
            showCharacters(favoriteCharacters)
        }
    }

    private fun showCharacters(favoritelist : List<CharactersItem>){
        characters.value = favoritelist
    }

    fun updateCharacter(character : CharactersItem){
        viewModelScope.launch {
            repository.updateCharacter(character)
        }
    }

}