package com.example.tmdb_team_g.Network

import com.example.tmdb_team_g.Response.MovieResponse
import com.example.tmdb_team_g.Response.TopMovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiServices {

    @GET("movie/popular")
    fun getMovieByApi(

        @Query("api_key") api_key: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1

    ) : Call<MovieResponse>


    @GET("movie/top_rated")
    fun getTopMovieByApi(
        @Query("api_key") api_key: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1

    ) : Call<TopMovieResponse>

}

