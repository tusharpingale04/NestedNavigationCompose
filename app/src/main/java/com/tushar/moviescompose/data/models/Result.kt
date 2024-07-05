package com.tushar.moviescompose.data.models


import com.google.gson.annotations.SerializedName

class Result(
    @SerializedName("id")
    val id: String?,
    @SerializedName("originalTitleText")
    val originalTitleText: OriginalTitleText?,
    @SerializedName("primaryImage")
    val primaryImage: PrimaryImage?,
    @SerializedName("releaseYear")
    val releaseYear: ReleaseYear?
)