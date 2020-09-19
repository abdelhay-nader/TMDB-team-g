package com.example.tmdb_team_g

data class TopMovieResponse (
    val results : List<resultsList2>
)


data class resultsList2 (

    val id : Long,
    val original_title : String,
    val poster_path : String,
    val original_language : String,
    val vote_average : Float,
    val overview : String,
    val release_date : String


)