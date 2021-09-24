package com.example.harrypotter.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.harrypotter.model.CharactersItem

class DetailViewModel : ViewModel() {


    val characterLiveData = MutableLiveData<CharactersItem>()

//    fun getDataS(uuid : Int ){
//
//
//        val character = CharactersItem(actor = "Ahmet",alive = true,"Ev", "www.ab.com")
//        characterLiveData.value = character
//    }

    fun getDataInfo(){

    }



}