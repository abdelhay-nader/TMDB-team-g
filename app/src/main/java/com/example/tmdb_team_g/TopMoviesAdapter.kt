package com.example.tmdb_team_g

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_top_api_movie.view.*

class TopMoviesAdapter (private val topMoviesList: List<resultsList2>) : RecyclerView.Adapter<TopMoviesAdapter.TopMovieViewHolder>()
{
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TopMoviesAdapter.TopMovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val movieViewLayout = layoutInflater.inflate(R.layout.item_top_api_movie, parent, false)
        return TopMovieViewHolder(movieViewLayout)

    }

    override fun onBindViewHolder(holder: TopMoviesAdapter.TopMovieViewHolder, position: Int) {
        holder.bindTopMovieData(topMoviesList.get(position))

    }

    override fun getItemCount(): Int {
        return topMoviesList.size

    }

    inner class TopMovieViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        val topMovieTitle : TextView = itemView.item_TopMovieTitle_icon
        val topMovieImage : ImageView = itemView.item_TopMovieImage_icon

        fun bindTopMovieData (topMovie : resultsList2){

            topMovieTitle.text = topMovie.original_title
            Picasso.get().load("https://image.tmdb.org/t/p/w500/${topMovie.poster_path}").into(topMovieImage)

        }

    }

}