package com.example.harrypotter.detail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.harrypotter.ViewModel.BaseViewModel
import com.example.harrypotter.data.model.CharactersItem
import com.example.harrypotter.data.local.CharacterDatabase
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : BaseViewModel(application) {


    val characterLiveData = MutableLiveData<CharactersItem>()

    fun getDataFromRoom(uuid : Int){
        viewModelScope.launch {
            val dao = CharacterDatabase(getApplication()).characterDao()
            val character = dao.getCharacter(uuid)
            characterLiveData.value = character
        }
    }

    fun updateCharacter(character : CharactersItem){
        viewModelScope.launch {
            val dao = CharacterDatabase(getApplication()).characterDao()
            dao.updateCharacter(character)

        }

    }





}