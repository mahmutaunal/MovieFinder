package com.mahmutalperenunal.moviesearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.mahmutalperenunal.moviesearch.api.RetrofitInstance
import com.mahmutalperenunal.moviesearch.databinding.ActivityMovieDetailBinding
import com.mahmutalperenunal.moviesearch.model.MovieDetailData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    private val apiKey = "67a11985"

    private var id: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        id = intent.getStringExtra("Movie Id").toString()

        getMovieData()
    }


    override fun onSupportNavigateUp(): Boolean {
        finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        return true
    }


    //get movie data
    private fun getMovieData() {

        RetrofitInstance.instance.getMovieDetail(id, apiKey).enqueue(object:
            Callback<MovieDetailData> {
            override fun onResponse(call: Call<MovieDetailData>, response: Response<MovieDetailData>) {

                response.body().let {
                    binding.movieTitle.text = response.body()!!.title
                    binding.movieGenre.text = response.body()!!.genre
                    binding.moviePlot.text = response.body()!!.plot
                    binding.movieReleaseDate.text = response.body()!!.releaseDate

                    Glide.with(this@MovieDetailActivity)
                        .load(response.body()!!.poster)
                        .centerCrop()
                        .placeholder(R.drawable.default_movie_poster)
                        .into(binding.moviePoster)
                }

            }

            override fun onFailure(call: Call<MovieDetailData>, t: Throwable) {

                Log.e("Error", t.printStackTrace().toString())

            }

        })

    }


    //back to mainActivity page
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}