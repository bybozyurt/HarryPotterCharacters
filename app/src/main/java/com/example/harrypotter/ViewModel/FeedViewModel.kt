package com.example.harrypotter.ViewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.harrypotter.model.CharactersItem
import com.example.harrypotter.service.CharacterDatabase
import com.example.harrypotter.service.CharactersService
import com.example.harrypotter.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedViewModel(application: Application) : BaseViewModel(application){

    private val charactersService = CharactersService()
    private val disposable = CompositeDisposable()
    private var customPreferences = CustomSharedPreferences(getApplication())
    //nano second
    private var refreshTime = 10 * 60 * 1000 * 1000 * 1000L

    val character = MutableLiveData<List<CharactersItem>>()
    val characterError = MutableLiveData<Boolean>()
    val characterLoading = MutableLiveData<Boolean>()


    fun refreshData(){
        val updateTime = customPreferences.getTime()
        if(updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime){
            getDataFromSQLite()
        } else{
            getDataFromAPI()
        }
    }

    fun refreshFromApi(){
        getDataFromAPI()
    }

    private fun getDataFromSQLite(){
        characterLoading.value = true
        launch {
            val characters = CharacterDatabase(getApplication()).characterDao().getAllCharacters()
            showCharacters(characters)
            Toast.makeText(getApplication(),"Characters from SQLite",Toast.LENGTH_LONG).show()
        }
    }

    private fun getDataFromAPI(){
        characterLoading.value = true


        disposable.add(
            charactersService.getData()
               .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<CharactersItem>>(){
                   override fun onSuccess(t: List<CharactersItem>) {
                       storeInSQLite(t)
                       Toast.makeText(getApplication(),"Characters from API",Toast.LENGTH_LONG).show()
                    }
                    override fun onError(e: Throwable) {
                        characterLoading.value = false
                        characterError.value = true
                        e.printStackTrace()
                    }
               })
       )
   }

    private fun showCharacters(characterList : List<CharactersItem>){
        character.value = characterList
        characterError.value = false
        characterLoading.value = false
    }

    private fun storeInSQLite(list : List<CharactersItem>){
        launch {
            val dao = CharacterDatabase(getApplication()).characterDao()
            dao.deleteAllCharacters()
            val listLong = dao.insertAll(*list.toTypedArray())
            var i = 0
            while (i < list.size){
                list[i].uuid = listLong[i].toInt()
                i++
            }
            showCharacters(list)
        }

        customPreferences.saveTime(System.nanoTime())
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}



