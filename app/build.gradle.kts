plugins {
    alias(libs.plugins.movieapp.android.application)
    alias(libs.plugins.movieapp.android.compose)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.space.movieapp"
}

dependencies{
    implementation(project(":core:ui"))
    implementation(libs.androidx.core.splashscreen)
}