package com.space.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.space.data.local.dao.FavouriteMoviesDao
import com.space.data.local.entity.FavouriteMovieEntity

@Database(
    entities = [FavouriteMovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun favouriteMoviesDao(): FavouriteMoviesDao
}
