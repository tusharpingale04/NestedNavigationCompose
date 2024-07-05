package com.tushar.moviescompose.data.repository

import androidx.paging.PagingData
import com.tushar.moviescompose.database.entity.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getLatestMovies(): Flow<PagingData<Movie>>

    suspend fun getFavorites(): List<Movie>?

}