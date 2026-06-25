package com.space.moviedetails.data.di

import com.space.moviedetails.data.remote.api.MovieDetailsApi
import com.space.moviedetails.data.repository.MovieDetailsRepositoryImpl
import com.space.moviedetails.domain.repository.MovieDetailsRepository
import org.koin.dsl.module
import retrofit2.Retrofit

val movieDetailsDataModule = module {
    single<MovieDetailsApi> { get<Retrofit>().create(MovieDetailsApi::class.java) }
    single<MovieDetailsRepository> { MovieDetailsRepositoryImpl(get(), get()) }
}
