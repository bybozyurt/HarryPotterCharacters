package com.example.harrypotter.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.harrypotter.data.model.CharactersItem

@Database(entities = arrayOf(CharactersItem::class),version = 1)
abstract class CharacterDatabase : RoomDatabase() {

    abstract fun characterDao() : CharactersDao

//    companion object {
//       @Volatile  private var instance : CharacterDatabase? = null
//
//        private val lock = Any()
//
//        operator fun invoke(context: Context) = instance ?: synchronized(lock){
//            instance ?: makeDatabase(context).also {
//                instance = it
//            }
//        }
//
//        private fun makeDatabase(context : Context) = Room.databaseBuilder(context.applicationContext, CharacterDatabase::class.java, "characterdatabase" )
//            .build()
//    }
}