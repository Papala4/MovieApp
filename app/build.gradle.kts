plugins {
    alias(libs.plugins.movieapp.android.application)
    alias(libs.plugins.movieapp.android.compose)
}

android {
    namespace = "com.space.movieapp"
}

dependencies{
    implementation(project(":core:ui"))
    implementation(project(":feature:moviedetails:presentation")) //just for test
    implementation(project(":feature:moviedetails:data"))
    implementation(project(":core:network"))
    implementation(libs.androidx.core.splashscreen)
}