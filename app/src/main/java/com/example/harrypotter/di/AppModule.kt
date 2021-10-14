//package com.example.harrypotter.di
//
//import com.example.harrypotter.data.remote.CharactersApi
//import com.example.harrypotter.util.Constants
//import dagger.Module
//import dagger.hilt.InstallIn
//import dagger.hilt.android.scopes.ActivityScoped
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//@Module
//@InstallIn(ActivityScoped::class)
//object AppModule {
//
//
//    fun getRetroInstance(): CharactersApi {
//        return Retrofit.Builder()
//            .baseUrl(Constants.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(CharactersApi::class.java)
//    }
//}