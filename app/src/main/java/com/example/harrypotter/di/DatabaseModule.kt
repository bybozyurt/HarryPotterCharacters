package com.example.harrypotter.di

import android.content.Context
import androidx.room.Room
import com.example.harrypotter.data.local.CharacterDatabase
import com.example.harrypotter.data.local.CharactersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Ahmet Bozyurt on 3.11.2021
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun getAppDatabase(@ApplicationContext context: Context): CharacterDatabase {
        return Room.databaseBuilder(
            context,
            CharacterDatabase::class.java,
            "characterdatabase"
        ).build()
    }

    @Singleton
    @Provides
    fun getAppDao(characterDatabase: CharacterDatabase): CharactersDao {
        return characterDatabase.characterDao()
    }
}