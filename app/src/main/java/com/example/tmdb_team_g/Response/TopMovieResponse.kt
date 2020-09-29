package com.example.tmdb_team_g.Response

import androidx.room.Entity
import androidx.room.PrimaryKey

data class TopMovieResponse (
    val results : List<resultsList2>
)

@Entity(tableName = "movie_table_2")
data class resultsList2 (

    @PrimaryKey
    val id : Long,

    val original_title : String,
    val poster_path : String,
    val original_language : String,
    val vote_average : Float,
    val overview : String,
    val release_date : String


)