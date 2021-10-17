package com.example.harrypotter.favorite

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.harrypotter.ViewModel.BaseViewModel
import com.example.harrypotter.data.model.CharactersItem
import com.example.harrypotter.data.local.CharacterDatabase
import com.example.harrypotter.repository.CharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(application: Application, private val repository: CharactersRepository) : BaseViewModel(application) {

    val characters = MutableLiveData<List<CharactersItem>>()

    fun getFavoriteCharactersFromSQLite(){
        viewModelScope.launch {
            //val favoriteCharacters = CharacterDatabase(getApplication()).characterDao().getFavoriteCharacters()
            val favoriteCharacters = repository.getFavoriteCharacters()
            showCharacters(favoriteCharacters)
        }
    }

    private fun showCharacters(favoritelist : List<CharactersItem>){
        characters.value = favoritelist
    }

    fun updateCharacter(character : CharactersItem){
        viewModelScope.launch {
//            val dao = CharacterDatabase(getApplication()).characterDao()
//            dao.updateCharacter(character)
            repository.updateCharacter(character)
        }
    }





}