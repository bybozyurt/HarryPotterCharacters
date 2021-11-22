package com.example.harrypotter.domain.repository

import com.example.harrypotter.data.model.CharactersItem
import com.example.harrypotter.domain.model.Characters
import com.example.harrypotter.util.extesions.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Call

/**
 * Created by Ahmet Bozyurt on 19.11.2021
 */
interface CharactersRepository {

    fun getCharacterList() : Flow<Resource<List<Characters>>>


}