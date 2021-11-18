package com.example.harrypotter.ui.feed

import com.example.harrypotter.data.model.CharactersItem

sealed class FeedViewState{
    object ShowSqliteMessage : FeedViewState()
    object ShowApiMessage : FeedViewState()

    data class FeedErrorViewState(val stateError: Boolean) : FeedViewState()
    data class FeedLoadingViewState(val stateLoading: Boolean) : FeedViewState()
    data class FeedCharacterList(val characterList: List<CharactersItem>) : FeedViewState()
}
