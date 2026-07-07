package com.space.movieapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.space.home.screen.HomeScreen
import com.space.ui.component.banners.NoInternetBanner
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

        enableEdgeToEdge()
        setContent {
            MovieAppTheme {
                val isOnline by vm.isOnline.collectAsStateWithLifecycle()

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MovieTheme.colors.background)
                ) {
                    Scaffold { innerPadding ->
//                        MovieDetailsScreen(
//                            movieId = 4347,
//                            modifier = Modifier.padding(paddingValues = innerPadding)
//                        )

                        HomeScreen(
                            modifier = Modifier.padding(paddingValues = innerPadding)
                        )
                    }

                    NoInternetBanner(
                        visible = !isOnline,
                        windowInsets = WindowInsets.navigationBars,
                        modifier = Modifier.align(Alignment.BottomCenter)
                    )
                }
            }
        }
    }
}