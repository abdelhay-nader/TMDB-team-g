package com.example.tmdb_team_g.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tmdb_team_g.Repository.MoviesRepository
import com.example.tmdb_team_g.Response.resultsList
import com.example.tmdb_team_g.Response.resultsList2

class MainViewModel(application: Application) : AndroidViewModel(application),
    MoviesRepository.MovieCallback {


    var page:Int = 1


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

        MoviesRepository.requestMovieData(this)

    }

    fun loadTopMovieData(){

        if (this :: topMovieData.isInitialized){
            _topMovieLiveData.value = topMovieData
            return
        }

        MoviesRepository.requestTopMovieData(this)
    }



    fun loadNextMoviePage(){

        var page= page++

        MoviesRepository.requestMovieData2(movieData as ArrayList<resultsList>,this,page)

    }


    fun loadNextTopMoviePage(){

        var page= page++

        MoviesRepository.requestTopMovieData2(topMovieData as ArrayList<resultsList2>,this,page)

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