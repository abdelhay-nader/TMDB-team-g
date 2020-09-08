package com.example.tmdb_team_g

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(), MoviesRepository.MovieCallback {


    private val _movieLiveData: MutableLiveData<List<resultsList>> = MutableLiveData()
    val movieLiveData: LiveData<List<resultsList>>
        get() = _movieLiveData


    private val _onError: MutableLiveData<String> = MutableLiveData()
    val onError: LiveData<String>
        get() = _onError

    private lateinit var movieData: List<resultsList>



    fun loadMovieData() {

        if (this :: movieData.isInitialized){
            _movieLiveData.value = movieData
            return
        }

        MoviesRepository.requestMovieData( this)

    }






    override fun onMovieReady(Movie: List<resultsList>) {
        movieData = Movie
        _movieLiveData.value = movieData
    }

    override fun onMovieLoadingError(errorMsg: String) {
        _onError.value = errorMsg
    }




}