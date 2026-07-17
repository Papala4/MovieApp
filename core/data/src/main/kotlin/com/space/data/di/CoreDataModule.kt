package com.space.data.di

import androidx.room.Room
import com.space.data.local.database.MovieDatabase
import com.space.data.local.mapper.FavouriteMovieMapper
import com.space.data.local.repository.FavouriteMoviesRepositoryImpl
import com.space.domain.repository.FavouriteMoviesRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreDataModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java,
            "movie_database"
        ).build()
    }
    single { get<MovieDatabase>().favouriteMoviesDao() }
    single { FavouriteMovieMapper() }
    single<FavouriteMoviesRepository> { FavouriteMoviesRepositoryImpl(get(), get()) }
}
