package com.example.harrypotter.ViewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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