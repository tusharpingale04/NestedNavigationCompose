package com.tushar.moviescompose.viewmodels

import androidx.compose.runtime.Immutable
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.tushar.moviescompose.base.BaseViewModel
import com.tushar.moviescompose.data.repository.MoviesRepository
import com.tushar.moviescompose.state.MoviesState
import com.tushar.moviescompose.state.UIPagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: MoviesRepository
) : BaseViewModel<MoviesState>(MoviesState.initialState) {

    private val moviesFlow: MutableSharedFlow<PagingData<MovieInfo>> = MutableSharedFlow(replay = 1)

    init {
        setMoviesState()
        getMovies()
    }

    private fun setMoviesState() {
        setState { state ->
            state.copy(
                uiPagingData = UIPagingData(
                    moviesFlow
                )
            )
        }
    }

    private fun getMovies() {
        viewModelScope.launch {
            repository.getLatestMovies()
                .map { pagingData ->
                    pagingData.map { movie ->
                        MovieInfo(
                            id = movie.id,
                            title = movie.title,
                            image = movie.imageUrl,
                            releaseYear = movie.releaseYear
                        )
                    }
                }
                .cachedIn(viewModelScope)
                .flowOn(Dispatchers.IO)
                .collectLatest {
                    moviesFlow.emit(it)
                }
        }
    }

    @Immutable
    data class MovieInfo(
        val id: String,
        val title: String?,
        val image: String?,
        val releaseYear: Int?
    )
}