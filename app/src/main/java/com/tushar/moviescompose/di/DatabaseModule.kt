package com.tushar.moviescompose.di

import android.content.Context
import com.tushar.moviescompose.database.MoviesDatabase
import com.tushar.moviescompose.database.dao.MoviesDao
import com.tushar.moviescompose.database.dao.RemoteKeyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideMoviesDB(@ApplicationContext context: Context): MoviesDatabase {
        return MoviesDatabase.getInstance(context)
    }

    @Provides
    fun provideMoviesDao(db: MoviesDatabase): MoviesDao {
        return db.moviesDao()
    }

    @Provides
    fun provideRemoteKeysDao(db: MoviesDatabase): RemoteKeyDao {
        return db.remoteKeysDao()
    }

}