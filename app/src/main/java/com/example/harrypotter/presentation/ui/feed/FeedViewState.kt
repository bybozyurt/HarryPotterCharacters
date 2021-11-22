package com.example.harrypotter.presentation.ui.feed

import com.example.harrypotter.data.model.CharactersItem
import com.example.harrypotter.domain.model.Characters
import com.example.harrypotter.util.extesions.Resource

sealed class FeedViewState{
    object ShowSqliteMessage : FeedViewState()
    object ShowApiMessage : FeedViewState()
    object Loading : FeedViewState()

    data class FeedErrorViewState(val stateError: Boolean) : FeedViewState()
    data class FeedLoadingViewState(val stateLoading: Boolean) : FeedViewState()
    data class FeedCharacterList(val characterList: List<CharactersItem>) : FeedViewState()
    data class FeedCha(val characterList: Any) : FeedViewState()
    //data class FeedCharacterList(val characterList: List<Characters>) : FeedViewState()
}
