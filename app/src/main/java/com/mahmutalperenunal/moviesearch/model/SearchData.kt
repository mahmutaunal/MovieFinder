package com.mahmutalperenunal.moviesearch.model

import com.google.gson.annotations.SerializedName

data class SearchData (
    @SerializedName("Search") val data: List<MovieData>,
    @SerializedName("Response") val response: String
)