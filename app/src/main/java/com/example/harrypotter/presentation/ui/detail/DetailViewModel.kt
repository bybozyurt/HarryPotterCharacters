package com.example.harrypotter.presentation.ui.detail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.harrypotter.data.model.CharactersItem
import com.example.harrypotter.data.repository.CharactersRepositoryImpl
import com.example.harrypotter.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(application: Application, private val repository: CharactersRepositoryImpl) : BaseViewModel(application) {

    val characterLiveData = MutableLiveData<CharactersItem>()

    fun getDataFromRoom(uuid : Int){
        viewModelScope.launch {
            val character = repository.getCharacter(uuid)
            characterLiveData.value = character
        }
    }

    fun updateCharacter(character : CharactersItem){
        viewModelScope.launch {
            repository.updateCharacter(character)
        }
    }

}