package com.tushar.moviescompose.state

import com.tushar.moviescompose.base.BaseState

data class MovieDetailsState(
    val title: String? = null,
    val imageUrl: String? = null
): BaseState {
    companion object {
        val initialState: MovieDetailsState
            get() = MovieDetailsState()
    }
}
