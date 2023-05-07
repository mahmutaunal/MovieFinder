package com.mahmutalperenunal.moviesearch.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// RetrofitInstance is a singleton class that creates a Retrofit instance
object RetrofitInstance {

    // Base URL of the API
    private const val BASE_URL = "http://www.omdbapi.com/"

    // Retrofit instance
    val instance by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(RetrofitApiMovie::class.java)
    }

}