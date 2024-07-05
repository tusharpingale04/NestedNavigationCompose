package com.tushar.moviescompose.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.tushar.moviescompose.data.mediator.MoviesRemoteMediator
import com.tushar.moviescompose.database.MoviesDatabase
import com.tushar.moviescompose.database.entity.Movie
import com.tushar.moviescompose.network.NetworkService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Singleton
class MoviesRepositoryImpl @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: MoviesDatabase
): MoviesRepository {
    override fun getLatestMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            remoteMediator = MoviesRemoteMediator(
                moviesApiService = networkService,
                moviesDatabase = databaseService
            ),
            pagingSourceFactory = {
                databaseService.moviesDao().getMovies()
            }
        ).flow
    }

    override suspend fun getFavorites(): List<Movie>? {
        return databaseService.moviesDao().getAllFavoriteMovies()
    }
}