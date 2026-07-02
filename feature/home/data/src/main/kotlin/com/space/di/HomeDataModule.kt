package com.space.di

import com.space.remote.api.DiscoverMovieApi
import com.space.remote.api.GenreApi
import com.space.remote.api.PopularMovieApi
import com.space.remote.api.SearchMovieApi
import com.space.remote.datasource.GenreDataSource
import com.space.remote.datasource.GenreDataSourceImpl
import com.space.remote.mapper.GenreMapper
import com.space.remote.mapper.PopularMovieMapper
import com.space.remote.repository.DiscoverMovieRepositoryImpl
import com.space.remote.repository.GenreRepositoryImpl
import com.space.remote.repository.PopularMovieRepositoryImpl
import com.space.remote.repository.SearchMovieRepositoryImpl
import com.space.repository.DiscoverMovieRepository
import com.space.repository.GenreRepository
import com.space.repository.PopularMovieRepository
import com.space.repository.SearchMovieRepository
import org.koin.dsl.module
import retrofit2.Retrofit

val homeDataModule = module {
    single<PopularMovieApi> { get<Retrofit>().create(PopularMovieApi::class.java) }
    single<SearchMovieApi> { get<Retrofit>().create(SearchMovieApi::class.java) }
    single<DiscoverMovieApi> { get<Retrofit>().create(DiscoverMovieApi::class.java) }
    single<GenreApi> { get<Retrofit>().create(GenreApi::class.java) }
    single<GenreDataSource> { GenreDataSourceImpl(get(), get()) }
    single<PopularMovieRepository> { PopularMovieRepositoryImpl(get(), get()) }
    single<SearchMovieRepository> { SearchMovieRepositoryImpl(get(), get()) }
    single<DiscoverMovieRepository> { DiscoverMovieRepositoryImpl(get(), get()) }
    single<GenreRepository> { GenreRepositoryImpl(get(), get()) }
    single { PopularMovieMapper() }
    single { GenreMapper() }
}
