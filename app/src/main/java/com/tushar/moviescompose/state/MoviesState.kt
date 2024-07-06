package com.tushar.moviescompose.state

import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import com.tushar.moviescompose.base.BaseState
import com.tushar.moviescompose.viewmodels.MoviesViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Immutable
data class MoviesState(
    val uiPagingData: UIPagingData = UIPagingData()
): BaseState {
    companion object {
        val initialState: MoviesState
            get() = MoviesState()
    }
}

@Immutable
data class UIPagingData(
    val pagingData: Flow<PagingData<MoviesViewModel.MovieInfo>> = emptyFlow()
)

