package com.example.tmdb_team_g

data class MovieResponse(

    val results : List<resultsList>

)

data class resultsList (


    val original_title : String,
    val poster_path : String,
    val original_language : String,
    val vote_average : Float,
    val overview : String,
    val release_date : String

)