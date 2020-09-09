package com.example.tmdb_team_g

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [resultsList::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

 abstract fun movieDao() : MovieDao


    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            if (INSTANCE != null)
                return INSTANCE!!

            INSTANCE = Room.databaseBuilder(
                context.applicationContext, AppDatabase::class.java, "movie_db"
            ).allowMainThreadQueries().build()

            return INSTANCE!!
        }
    }

















}