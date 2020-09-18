package com.example.tmdb_team_g

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    //private val mainViewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = Navigation.findNavController(this,R.id.main_container_activityMain)
        NavigationUI.setupWithNavController(main_bottom_bar,navController)


       /* mainViewModel.movieLiveData.observe(this,Observer{bindMovieData(it)})

        mainViewModel.onError.observe(this, Observer{handelMovieError(it)})

        mainViewModel.loadMovieData()
       requestMovieData()*/
    }

//    private fun requestMovieData() {
//
//
//        MoviesRepository.requestMovieData( this)
//
//
//
//    }
// @SuppressLint("WrongConstant")
//    fun bindMovieData (movie : List<resultsList> ) {
//
//        recyclerview_tmdb.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayout.VERTICAL,false)
//        recyclerview_tmdb.adapter=MoviesAdapter(movie)
//
//
//    }
//
//    private fun handelMovieError(errorMsg: String) {
//        Toast.makeText(this@MainActivity, errorMsg, Toast.LENGTH_LONG).show()
//    }







}