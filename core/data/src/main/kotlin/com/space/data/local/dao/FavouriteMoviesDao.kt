package com.space.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.space.data.local.entity.FavouriteMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteMoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: FavouriteMovieEntity)

    @Query("DELETE FROM favourite_movies WHERE id = :movieId")
    suspend fun deleteById(movieId: Int)

    @Query("SELECT * FROM favourite_movies")
    fun getAll(): Flow<List<FavouriteMovieEntity>>
}
