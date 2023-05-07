package com.mahmutalperenunal.moviesearch.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mahmutalperenunal.moviesearch.MovieDetailActivity
import com.mahmutalperenunal.moviesearch.R
import com.mahmutalperenunal.moviesearch.model.MovieData

// MovieAdapter class is used to show movie list in RecyclerView
class MovieAdapter(
    private var listMovie: ArrayList<MovieData>, private val context: Context
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {


    // MovieViewHolder class is used to hold movie item views
    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.movie_title)
        val year: TextView = itemView.findViewById(R.id.movie_release_date)
        val poster: ImageView = itemView.findViewById(R.id.movie_poster)

        val item: ConstraintLayout = itemView.findViewById(R.id.movie_layout)
    }


    // onCreateViewHolder is used to create a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        )
    }


    // onBindViewHolder is used to bind data to ViewHolder
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        holder.title.text = listMovie[position].title
        holder.year.text = listMovie[position].releaseDate

        Glide.with(context)
            .load(listMovie[position].poster)
            .placeholder(R.drawable.default_movie_poster)
            .centerCrop()
            .into(holder.poster)


        holder.item.setOnClickListener {
            val intent = Intent(context, MovieDetailActivity::class.java).apply {
                putExtra("Movie Id", listMovie[position].imdbID)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        }
    }


    // getItemCount is used to get size of list
    override fun getItemCount(): Int {
        return listMovie.size
    }

}