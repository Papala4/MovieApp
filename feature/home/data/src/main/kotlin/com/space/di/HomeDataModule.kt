package com.space.di

import com.space.remote.api.DiscoverMovieApi
import com.space.remote.api.GenreApi
import com.space.remote.api.PopularMovieApi
import com.space.remote.api.SearchMovieApi
import com.space.remote.datasource.discover.DiscoverMovieDataSource
import com.space.remote.datasource.discover.DiscoverMovieDataSourceImpl
import com.space.remote.datasource.genre.GenreDataSource
import com.space.remote.datasource.genre.GenreDataSourceImpl
import com.space.remote.datasource.popular.PopularMovieDataSource
import com.space.remote.datasource.popular.PopularMovieDataSourceImpl
import com.space.remote.datasource.search.SearchMovieDataSource
import com.space.remote.datasource.search.SearchMovieDataSourceImpl
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
    single<DiscoverMovieApi> { get<Retrofit>().create(DiscoverMovieApi::class.java) }
    single<SearchMovieApi> { get<Retrofit>().create(SearchMovieApi::class.java) }
    single<GenreApi> { get<Retrofit>().create(GenreApi::class.java) }
    single<GenreDataSource> { GenreDataSourceImpl(get(), get()) }
    single<PopularMovieDataSource> { PopularMovieDataSourceImpl(get()) }
    single<SearchMovieDataSource> { SearchMovieDataSourceImpl(get()) }
    single<DiscoverMovieDataSource> { DiscoverMovieDataSourceImpl(get()) }
    single<PopularMovieRepository> { PopularMovieRepositoryImpl(get(), get(), get()) }
    single<DiscoverMovieRepository> { DiscoverMovieRepositoryImpl(get(), get(), get()) }
    single<SearchMovieRepository> { SearchMovieRepositoryImpl(get(), get(), get()) }
    single<GenreRepository> { GenreRepositoryImpl(get(), get()) }
    single { PopularMovieMapper() }
    single { GenreMapper() }
}
