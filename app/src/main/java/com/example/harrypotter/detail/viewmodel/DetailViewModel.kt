package com.example.harrypotter.detail.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.harrypotter.ViewModel.BaseViewModel
import com.example.harrypotter.model.CharactersItem
import com.example.harrypotter.service.CharacterDatabase
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : BaseViewModel(application) {


    val characterLiveData = MutableLiveData<CharactersItem>()

    fun getDataFromRoom(uuid : Int){
        launch {
            val dao = CharacterDatabase(getApplication()).characterDao()
            val character = dao.getCharacter(uuid)
            characterLiveData.value = character
        }
    }





}