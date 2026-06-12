plugins {
    id("movieapp.android.application")
    id("movieapp.android.koin")
}

android {
    namespace = "com.movieapp"

    defaultConfig {
        applicationId = "com.movieapp"
    }
}