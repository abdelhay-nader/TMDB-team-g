package com.example.tmdb_team_g.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tmdb_team_g.Dao.MovieDao
import com.example.tmdb_team_g.Dao.TopMovieDao
import com.example.tmdb_team_g.Response.resultsList
import com.example.tmdb_team_g.Response.resultsList2

@Database(entities = [resultsList::class, resultsList2::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

 abstract fun movieDao() : MovieDao
 abstract fun topMovieDao() : TopMovieDao


    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            if (INSTANCE != null)
                return INSTANCE!!

            INSTANCE = Room.databaseBuilder(
                context.applicationContext, AppDatabase::class.java, "movie_db"
            ).allowMainThreadQueries().fallbackToDestructiveMigration().build()

            return INSTANCE!!
        }
    }

















}