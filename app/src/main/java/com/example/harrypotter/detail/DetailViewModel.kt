package com.example.harrypotter.detail

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
class DetailViewModel @Inject constructor(application: Application, private val repository: CharactersRepository) : BaseViewModel(application) {


    val characterLiveData = MutableLiveData<CharactersItem>()

    fun getDataFromRoom(uuid : Int){
        viewModelScope.launch {
//            val dao = CharacterDatabase(getApplication()).characterDao()
//            val character = dao.getCharacter(uuid)
            val character = repository.getCharacter(uuid)
            characterLiveData.value = character
        }
    }

    fun updateCharacter(character : CharactersItem){
        viewModelScope.launch {
//            val dao = CharacterDatabase(getApplication()).characterDao()
//            dao.updateCharacter(character)
            repository.updateCharacter(character)

        }

    }





}