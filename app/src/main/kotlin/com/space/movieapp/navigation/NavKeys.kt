package com.space.movieapp.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object HomeKey : NavKey

@Serializable
data object FavoriteKey : NavKey

@Serializable
data class MovieDetailsKey(val movieId: Int) : NavKey
