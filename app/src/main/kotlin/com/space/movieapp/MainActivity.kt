package com.space.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.space.ui.component.SearchBar
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
            val isLoading by vm.isLoading.collectAsStateWithLifecycle()

            MovieAppTheme {
                if (!isLoading) {
                    SearchBar("",
                        {})
                }
            }
        }
    }
}
