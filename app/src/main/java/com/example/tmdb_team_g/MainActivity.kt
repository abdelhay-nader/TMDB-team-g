package com.example.tmdb_team_g

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MoviesRepository.MovieCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestMovieData()


    }

    private fun requestMovieData() {


        MoviesRepository.requestMovieData( this)



    }







    @SuppressLint("WrongConstant")
    fun bindMovieData (movie : List<resultsList> ) {

        recyclerview_tmdb.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayout.VERTICAL,false)
        recyclerview_tmdb.adapter=MoviesAdapter(movie)


    }

    override fun onMovieReady(Movie: List<resultsList>) {
        bindMovieData(Movie)
    }

    override fun onMovieLoadingError(errorMsg: String) {
        Toast.makeText(this@MainActivity, errorMsg, Toast.LENGTH_LONG).show()
    }



}