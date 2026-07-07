package com.space.moviedetails.di

import com.space.moviedetails.mapper.MovieDetailsUiMapper
import com.space.moviedetails.vm.MovieDetailsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val vmModule = module {
    factory { MovieDetailsUiMapper() }
    viewModel { MovieDetailsViewModel(get(), get(), get(), get(), get()) }
}