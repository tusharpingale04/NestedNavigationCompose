package com.tushar.moviescompose.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_table")
class Movie(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("id")
    val id: String,

    @ColumnInfo("imageUrl")
    val imageUrl: String?,

    @ColumnInfo("releaseYear")
    val releaseYear: Int?,

    @ColumnInfo("title")
    val title: String?,

    @ColumnInfo("favoriteTs")
    val favoriteTs: Long?
)