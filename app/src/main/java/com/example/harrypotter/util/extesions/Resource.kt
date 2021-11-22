package com.example.harrypotter.util.extesions

import com.example.harrypotter.data.model.CharactersItem
import com.example.harrypotter.presentation.ui.feed.FeedViewState

/**
 * Created by Ahmet Bozyurt on 19.11.2021
 */
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(data: T? = null) : Resource<T>(data)


//    data class FeedErrorViewState(val stateError: Boolean) : Resource<T>(stateError)
//    data class FeedLoadingViewState(val stateLoading: Boolean) : FeedViewState()
//    data class FeedCharacterList(val characterList: List<CharactersItem>) : FeedViewState()
}