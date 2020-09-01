package com.example.tmdb_team_g
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.item_api_movie.view.*

class MoviesAdapter (private val moviesList: List<resultsList>) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val movieViewLayout = layoutInflater.inflate(R.layout.item_api_movie, parent, false)
        return MovieViewHolder(movieViewLayout)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovieData(moviesList.get(position))
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }



    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val movieTitle : TextView  = itemView.title
        val movieImage : ImageView = itemView.poster

        fun bindMovieData (movie : resultsList){
            movieTitle.text = movie.original_title
            Picasso.get().load("https://image.tmdb.org/t/p/w500/${movie.poster_path}").into(movieImage)

        }

    }

}