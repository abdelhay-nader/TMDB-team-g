package com.example.tmdb_team_g

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(application: Application) : AndroidViewModel(application), MoviesRepository.MovieCallback {


    private val _movieLiveData: MutableLiveData<List<resultsList>> = MutableLiveData()
    val movieLiveData: LiveData<List<resultsList>>
        get() = _movieLiveData

    private val _topMovieLiveData :MutableLiveData<List<resultsList2>> = MutableLiveData()
    val topMovieLiveData : LiveData<List<resultsList2>>
        get() = _topMovieLiveData



    private val _onError: MutableLiveData<String> = MutableLiveData()
    val onError: LiveData<String>
        get() = _onError

    private lateinit var movieData: List<resultsList>

    private lateinit var topMovieData : List<resultsList2>

    init {
        MoviesRepository.createDatabase(application)
    }


    fun loadMovieData() {

        if (this :: movieData.isInitialized){
            _movieLiveData.value = movieData
            return
        }




        MoviesRepository.requestMovieData( this)

    }

    fun loadTopMovieData(){

        if (this :: topMovieData.isInitialized){
            _topMovieLiveData.value = topMovieData
            return
        }

        MoviesRepository.requestTopMovieData(this)
    }






    override fun onMovieReady(Movie: List<resultsList>) {
        movieData = Movie
        _movieLiveData.value = movieData
    }

    override fun onTopMovieReady(TopMovie: List<resultsList2>) {
        topMovieData = TopMovie
        _topMovieLiveData.value = topMovieData

    }

    override fun onMovieLoadingError(errorMsg: String) {
        _onError.value = errorMsg
    }




}