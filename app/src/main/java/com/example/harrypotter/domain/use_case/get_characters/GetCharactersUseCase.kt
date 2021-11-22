package com.example.harrypotter.domain.use_case.get_characters

import com.example.harrypotter.data.model.CharactersItem
import com.example.harrypotter.domain.repository.CharactersRepository
import com.example.harrypotter.util.extesions.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Ahmet Bozyurt on 19.11.2021
 */
class GetCharactersUseCase @Inject constructor(private val repository: CharactersRepository) {

    fun getCharacterList() = repository.getCharacterList()

}