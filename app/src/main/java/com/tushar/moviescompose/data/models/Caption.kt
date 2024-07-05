package com.tushar.moviescompose.data.models


import com.google.gson.annotations.SerializedName

class Caption(
    @SerializedName("plainText")
    val plainText: String?,
    @SerializedName("__typename")
    val typename: String?
)