plugins {
    alias(libs.plugins.movieapp.android.application)
    alias(libs.plugins.movieapp.android.compose)
}

android {
    namespace = "com.space.movieapp"
}

dependencies{
    implementation(project(":core:ui"))
    implementation(project(":core:network"))
    implementation(project(":feature:moviedetails:presentation"))
    implementation(project(":feature:moviedetails:data"))
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.bundles.lifecycle)
}