package com.space.di

import com.space.remote.api.GenreApi
import com.space.remote.api.PopularMovieApi
import com.space.remote.datasource.GenreDataSource
import com.space.remote.datasource.GenreDataSourceImpl
import com.space.remote.mapper.GenreMapper
import com.space.remote.mapper.PopularMovieMapper
import com.space.remote.repository.GenreRepositoryImpl
import com.space.remote.repository.PopularMovieRepositoryImpl
import com.space.repository.GenreRepository
import com.space.repository.PopularMovieRepository
import org.koin.dsl.module
import retrofit2.Retrofit

val homeDataModule = module {
    single<PopularMovieApi> { get<Retrofit>().create(PopularMovieApi::class.java) }
    single<GenreApi> { get<Retrofit>().create(GenreApi::class.java) }
    single<GenreDataSource> { GenreDataSourceImpl(get(), get()) }
    single<PopularMovieRepository> { PopularMovieRepositoryImpl(get(), get()) }
    single<GenreRepository> { GenreRepositoryImpl(get(), get()) }
    single { PopularMovieMapper() }
    single { GenreMapper() }
}
