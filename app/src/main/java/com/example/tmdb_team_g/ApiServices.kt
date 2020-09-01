package com.example.tmdb_team_g

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

}

