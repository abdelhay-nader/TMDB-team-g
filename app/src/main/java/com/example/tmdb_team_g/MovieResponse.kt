package com.example.tmdb_team_g

import androidx.room.Entity
import androidx.room.PrimaryKey

data class MovieResponse(

    val results : List<resultsList>

)
@Entity(tableName = "movie_table")
data class resultsList (

    @PrimaryKey
    val id : Long,

    val original_title : String,
    val poster_path : String,
    val original_language : String,
    val vote_average : Float,
    val overview : String,
    val release_date : String

)