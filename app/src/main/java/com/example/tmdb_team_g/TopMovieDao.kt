package com.example.tmdb_team_g

import androidx.room.*

    @Dao
    interface TopMovieDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun addTopMovies (movies: List<resultsList2>)

        @Query("SELECT * FROM movie_table_2")
        fun getAllTopMovies(): List<resultsList2>

        @Delete
        fun deleteTopMovie(movie: resultsList2)

        @Query("DELETE FROM movie_table_2")
        fun deleteAllTopMovies()


    }

