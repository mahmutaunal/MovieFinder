package com.mahmutalperenunal.moviesearch.api

import com.mahmutalperenunal.moviesearch.model.MovieDetailData
import com.mahmutalperenunal.moviesearch.model.SearchData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApiMovie {

    @GET("/")
    fun getMovie(
        @Query("s") search: String,
        @Query("apikey") apikey: String
    ): Call<SearchData>


    @GET("/")
    fun getMovieDetail(
        @Query("i") id: String,
        @Query("apikey") apikey: String
    ): Call<MovieDetailData>

}