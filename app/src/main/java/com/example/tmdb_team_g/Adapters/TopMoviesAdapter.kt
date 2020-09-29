package com.example.tmdb_team_g.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb_team_g.Activity.DetailsActivity
import com.example.tmdb_team_g.R
import com.example.tmdb_team_g.Response.resultsList2
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_top_api_movie.view.*

class TopMoviesAdapter (val mContext: Context, private val topMoviesList: List<resultsList2>) : RecyclerView.Adapter<TopMoviesAdapter.TopMovieViewHolder>()
{
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TopMovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val movieViewLayout = layoutInflater.inflate(R.layout.item_top_api_movie, parent, false)
        return TopMovieViewHolder(movieViewLayout)

    }

    override fun onBindViewHolder(holder: TopMovieViewHolder, position: Int) {
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

        init {
            itemView.setOnClickListener(View.OnClickListener {
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    val clickedDataItem: resultsList2 = topMoviesList.get(pos)
                    val intent = Intent(mContext, DetailsActivity::class.java)

                    intent.putExtra("overView","${clickedDataItem.overview}")
                    intent.putExtra("releaseDate","${clickedDataItem.release_date}")
                    intent.putExtra("original_language","${clickedDataItem.original_language}")
                    intent.putExtra("poster_path", "${clickedDataItem.poster_path}")



                    mContext.startActivity(intent)


                }
            })
        }


















    }

}