package com.example.harrypotter.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.harrypotter.model.CharactersItem
import com.example.harrypotter.service.CharactersService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FeedViewModel : ViewModel(){

    private val charactersService = CharactersService()
    private val disposable = CompositeDisposable()

    val character = MutableLiveData<List<CharactersItem>>()
    val characterError = MutableLiveData<Boolean>()
    val characterLoading = MutableLiveData<Boolean>()


    fun refreshData(){
        getDataFromAPI()

    }

    private fun getDataFromAPI(){
        characterLoading.value = true


        disposable.add(
            charactersService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<CharactersItem>>(){
                    override fun onSuccess(t: List<CharactersItem>) {
                        character.value = t
                        characterError.value = false
                        characterLoading.value = false
                    }

                    override fun onError(e: Throwable) {
                        characterLoading.value = false
                        characterError.value = true
                        e.printStackTrace()
                    }

                })
        )
    }
}