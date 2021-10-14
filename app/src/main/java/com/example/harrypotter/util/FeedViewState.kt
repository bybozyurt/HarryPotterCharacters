package com.example.harrypotter.util

import com.example.harrypotter.data.model.CharactersItem

sealed class FeedViewState{
    data class FeedErrorViewState(val stateError: Boolean) : FeedViewState()
    data class FeedLoadingViewState(val stateLoading: Boolean) : FeedViewState()
    data class FeedCharacterList(val characterList: List<CharactersItem>) : FeedViewState()
}
