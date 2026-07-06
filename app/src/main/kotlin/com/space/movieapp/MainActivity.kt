package com.space.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.space.ui.theme.MovieAppTheme

class MainActivity : ComponentActivity() {
    private val vm: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashScreen = installSplashScreen()

        splashScreen.setKeepOnScreenCondition {
            vm.isLoading.value
        }

        enableEdgeToEdge()
        setContent {
            MovieAppTheme {

            }
        }
    }
}
