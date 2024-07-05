package com.tushar.moviescompose.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tushar.moviescompose.database.entity.Movie

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<Movie>)

    @Query("SELECT * from movies_table")
    fun getMovies(): PagingSource<Int, Movie>

    @Query("SELECT * from movies_table WHERE favoriteTs IS NOT NULL ORDER BY favoriteTs ASC")
    suspend fun getAllFavoriteMovies(): List<Movie>?

    @Query("DELETE FROM movies_table")
    suspend fun clearAll()

}