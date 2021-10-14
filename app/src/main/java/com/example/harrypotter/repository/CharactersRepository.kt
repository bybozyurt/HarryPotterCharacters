package com.example.harrypotter.repository

import com.example.harrypotter.data.model.CharactersItem
import com.example.harrypotter.data.remote.CharactersApi
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class CharactersRepository @Inject constructor(private val api : CharactersApi){


}