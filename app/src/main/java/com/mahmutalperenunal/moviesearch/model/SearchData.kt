package com.mahmutalperenunal.moviesearch.model

import com.google.gson.annotations.SerializedName

// This class is used to store the data of the movie.
data class SearchData(
    @SerializedName("Search") val data: List<MovieData>,
    @SerializedName("Response") val response: String
)