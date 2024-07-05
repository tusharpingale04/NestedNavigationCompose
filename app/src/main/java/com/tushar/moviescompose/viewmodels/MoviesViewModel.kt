package com.tushar.moviescompose.viewmodels

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.tushar.moviescompose.data.mediator.MoviesRemoteMediator
import com.tushar.moviescompose.data.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.internal.ChannelFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {

    private val _movies = MutableSharedFlow<PagingData<MovieInfo>>()
    val movies: Flow<PagingData<MovieInfo>> = repository.getLatestMovies().map { pagingData ->
        pagingData.map { movie ->
            MovieInfo(
                id = movie.id,
                title = movie.title,
                image = movie.imageUrl,
                releaseYear = movie.releaseYear
            )
        }
    }.cachedIn(viewModelScope)

    @Immutable
    data class MovieInfo(
        val id: String,
        val title: String?,
        val image: String?,
        val releaseYear: Int?
    )
}