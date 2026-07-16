package com.space.movieapp.ui

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.space.movieapp.navigation.MovieNavGraph
import com.space.ui.theme.MovieAppTheme
import com.space.ui.theme.MovieTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val vm: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition {
            vm.isLoading.value
        }

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
        )
        setContent {
            MovieAppTheme {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MovieTheme.colors.background)
                ) {
                    MovieNavGraph()
                }
            }
        }
    }
}