package com.example.harrypotter.domain.use_case.get_characters

import com.example.harrypotter.data.model.CharactersItem
import com.example.harrypotter.domain.model.Characters
import com.example.harrypotter.domain.repository.CharactersRepository
import com.example.harrypotter.util.extesions.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Created by Ahmet Bozyurt on 19.11.2021
 */
@AndroidEntryPoint
class GetCharactersUseCase @Inject constructor(private val repository: CharactersRepository) {

    fun getCharacterList() = repository.getCharacterList()


    
}