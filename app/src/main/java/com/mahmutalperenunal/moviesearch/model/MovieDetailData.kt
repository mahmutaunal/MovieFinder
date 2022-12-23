package com.mahmutalperenunal.moviesearch.model

import com.google.gson.annotations.SerializedName

data class MovieDetailData (
    @SerializedName("Title") val title: String,
    @SerializedName("Plot") val plot: String,
    @SerializedName("Year") val releaseDate: String,
    @SerializedName("Genre") val genre: String,
    @SerializedName("Poster") val poster: String
)