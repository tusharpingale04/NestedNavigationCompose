package com.tushar.moviescompose.network

import com.tushar.moviescompose.data.models.MoviesPage
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("titles/x/upcoming")
    suspend fun getLatestMovies(
        @Query("page") page: Int
    ): MoviesPage

}