package com.tushar.moviescompose.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tushar.moviescompose.database.dao.MoviesDao
import com.tushar.moviescompose.database.dao.RemoteKeyDao
import com.tushar.moviescompose.database.entity.Movie
import com.tushar.moviescompose.database.entity.RemoteKeyEntity

@Database(entities = [Movie::class, RemoteKeyEntity::class], version = 1, exportSchema = true)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao
    abstract fun remoteKeysDao(): RemoteKeyDao

    companion object {
        @Synchronized
        fun getInstance(context: Context): MoviesDatabase {
            return Room.databaseBuilder(
                context,
                MoviesDatabase::class.java,
                "movies.db"
            ).fallbackToDestructiveMigration().build()
        }
    }

}