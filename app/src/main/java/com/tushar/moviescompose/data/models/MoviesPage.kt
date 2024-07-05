package com.tushar.moviescompose.data.models


import com.google.gson.annotations.SerializedName

class MoviesPage(
    @SerializedName("entries")
    val entries: Int?,
    @SerializedName("next")
    val next: String?,
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<Result>?
)