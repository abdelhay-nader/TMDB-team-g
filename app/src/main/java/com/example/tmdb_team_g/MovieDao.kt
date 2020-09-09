package com.example.tmdb_team_g

import androidx.room.*

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovies (movies: List<resultsList>)

    @Query("SELECT * FROM movie_table")
    fun getAllMovies(): List<resultsList>

    @Delete
    fun deleteMovie(movie: resultsList)

    @Query("DELETE FROM movie_table")
    fun deleteAll()





}