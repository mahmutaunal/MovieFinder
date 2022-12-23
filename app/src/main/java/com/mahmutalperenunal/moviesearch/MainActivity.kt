package com.mahmutalperenunal.moviesearch

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.mahmutalperenunal.moviesearch.adapter.MovieAdapter
import com.mahmutalperenunal.moviesearch.api.RetrofitInstance
import com.mahmutalperenunal.moviesearch.databinding.ActivityMainBinding
import com.mahmutalperenunal.moviesearch.model.MovieData
import com.mahmutalperenunal.moviesearch.model.SearchData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val movieList = ArrayList<MovieData>()

    private val apiKey = "67a11985"

    private var title: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        //get movie data
        binding.searchButton.setOnClickListener { getMovieData() }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.tema -> AlertDialog.Builder(this, R.style.CustomAlertDialog)
                .setTitle(R.string.app_theme_text)
                .setMessage(R.string.choose_app_theme_text)
                .setPositiveButton(
                    R.string.light_theme_text
                ) { _: DialogInterface?, _: Int ->
                    AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_NO
                    )
                }
                .setNegativeButton(
                    R.string.dark_theme_text
                ) { _: DialogInterface?, _: Int ->
                    AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_YES
                    )
                }
                .setNeutralButton(
                    R.string.system_theme_text
                ) { _: DialogInterface?, _: Int ->
                    AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                    )
                }
                .show()
        }
        return true
    }



    //get movie data
    private fun getMovieData() {

        binding.progressBar.visibility = View.VISIBLE

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)

        title = binding.searchEditText.text.toString().trim()

        if (title.isEmpty()) {

            binding.progressBar.visibility = View.GONE

            Toast.makeText(applicationContext, R.string.enter_film_name_text, Toast.LENGTH_SHORT).show()

            binding.searchEditTextLayout.error = resources.getString(R.string.enter_film_name_text)

        } else {

            binding.searchEditTextLayout.isErrorEnabled = false

            RetrofitInstance.instance.getMovie(title, apiKey).enqueue(object: Callback<SearchData> {
                override fun onResponse(call: Call<SearchData>, response: Response<SearchData>) {

                    if (response.body()!!.response == "True") {

                        binding.noMovieImageView.visibility = View.GONE
                        binding.noMovieTextView.visibility = View.GONE

                        binding.nestedScrollView.visibility = View.VISIBLE

                        binding.progressBar.visibility = View.GONE

                        response.body().let {
                            movieList.clear()
                            movieList.addAll(it!!.data)
                        }

                        val adapter = MovieAdapter(movieList, applicationContext)
                        binding.recyclerView.adapter = adapter

                    } else {

                        binding.progressBar.visibility = View.GONE

                        binding.noMovieImageView.visibility = View.VISIBLE
                        binding.noMovieTextView.visibility = View.VISIBLE

                        binding.nestedScrollView.visibility = View.GONE

                    }

                }

                override fun onFailure(call: Call<SearchData>, t: Throwable) {

                    Log.e("Error", t.printStackTrace().toString())

                    binding.progressBar.visibility = View.GONE

                    binding.noMovieImageView.visibility = View.VISIBLE
                    binding.noMovieTextView.visibility = View.VISIBLE

                    binding.nestedScrollView.visibility = View.GONE

                }

            })

        }

    }


    //exit app on back button pressed
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

}