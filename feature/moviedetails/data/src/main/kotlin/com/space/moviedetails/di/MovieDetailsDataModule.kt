package com.space.moviedetails.di

import com.space.moviedetails.remote.api.MovieDetailsApi
import com.space.moviedetails.remote.datasource.MovieDetailsDataSource
import com.space.moviedetails.remote.datasource.MovieDetailsDataSourceImpl
import com.space.moviedetails.remote.mapper.MovieDetailsMapper
import com.space.moviedetails.remote.repository.MovieDetailsRepositoryImpl
import com.space.moviedetails.repository.MovieDetailsRepository
import org.koin.dsl.module
import retrofit2.Retrofit

val movieDetailsDataModule = module {
    single<MovieDetailsApi> { get<Retrofit>().create(MovieDetailsApi::class.java) }
    single<MovieDetailsDataSource> { MovieDetailsDataSourceImpl(get(), get()) }
    single<MovieDetailsRepository> { MovieDetailsRepositoryImpl(get(), get()) }
    single { MovieDetailsMapper() }
}
