package com.example.harrypotter.di

import android.content.Context
import androidx.room.Room
import com.example.harrypotter.data.local.CharacterDatabase
import com.example.harrypotter.data.local.CharactersDao
import com.example.harrypotter.data.remote.CharactersApi
import com.example.harrypotter.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun getAppDatabase(@ApplicationContext context: Context) : CharacterDatabase{
        return Room.databaseBuilder(
            context,
            CharacterDatabase::class.java,
            "characterdatabase"
        ).build()
    }

    @Provides
    @Singleton
    fun getAppDao(characterDatabase: CharacterDatabase) : CharactersDao{
        return characterDatabase.characterDao()
    }

    @Provides
    @Singleton
    fun getRetroServiceInstance(retrofit: Retrofit) : CharactersApi{
        return retrofit.create(CharactersApi::class.java)
    }

    @Provides
    @Singleton
    fun getRetroInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
}