package com.tushar.moviescompose.viewmodels

import com.tushar.moviescompose.base.BaseViewModel
import com.tushar.moviescompose.data.repository.MoviesRepository
import com.tushar.moviescompose.state.MovieDetailsState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel(assistedFactory = MovieDetailsViewModel.MovieDetailsFactory::class)
class MovieDetailsViewModel @AssistedInject constructor(
    @Assisted private val id: String,
    private var repository: MoviesRepository
): BaseViewModel<MovieDetailsState>(MovieDetailsState.initialState) {

    init {
        println("why gg:: id: $id")
    }

    @AssistedFactory
    interface MovieDetailsFactory {
        fun create(id: String): MovieDetailsViewModel
    }
}