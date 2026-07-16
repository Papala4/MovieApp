package com.space.moviedetails.di

import com.space.moviedetails.mapper.MovieDetailsUiMapper
import com.space.moviedetails.vm.MovieDetailsVm
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val vmModule = module {
    single { MovieDetailsUiMapper() }
    viewModel { MovieDetailsVm(get(), get(), get(), get(), get()) }
}