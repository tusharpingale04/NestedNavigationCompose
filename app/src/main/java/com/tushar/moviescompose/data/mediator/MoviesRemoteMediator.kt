package com.tushar.moviescompose.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.tushar.moviescompose.database.MoviesDatabase
import com.tushar.moviescompose.database.entity.Movie
import com.tushar.moviescompose.database.entity.RemoteKeyEntity
import com.tushar.moviescompose.network.NetworkService

@OptIn(ExperimentalPagingApi::class)
class MoviesRemoteMediator constructor(
    private val moviesApiService: NetworkService,
    private val moviesDatabase: MoviesDatabase,
) : RemoteMediator<Int, Movie>() {

    private val REMOTE_KEY_ID = "movie_remote_id"

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Movie>,
    ): MediatorResult {
        println("why gg:: load")
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val remoteKey = moviesDatabase.remoteKeysDao().getById(REMOTE_KEY_ID)
                    if (remoteKey == null || remoteKey.nextOffset == 0)
                        return MediatorResult.Success(endOfPaginationReached = true)
                    remoteKey.nextOffset
                }
            }

            val apiResponse = moviesApiService.getLatestMovies(
                page = page
            )
            val results = apiResponse.results ?: emptyList()
            val nextOffset = ((apiResponse.page ?: 0) + 1)

            moviesDatabase.withTransaction {
                moviesDatabase.moviesDao().insertAll(
                    results.mapNotNull { movie ->
                        if (movie.id != null && movie.originalTitleText?.text != null) {
                            Movie(
                                id = movie.id,
                                imageUrl = movie.primaryImage?.url,
                                releaseYear = movie.releaseYear?.year,
                                title = movie.originalTitleText.text,
                                favoriteTs = null
                            )
                        } else null
                    }
                )
                moviesDatabase.remoteKeysDao().insert(
                    RemoteKeyEntity(
                        id = REMOTE_KEY_ID,
                        nextOffset = nextOffset,
                    )
                )
            }
            MediatorResult.Success(endOfPaginationReached = results.size < state.config.pageSize)
        } catch (e: Exception) {
            println("why gg:: exception: ${e.message}")
            MediatorResult.Error(e)
        }
    }
}