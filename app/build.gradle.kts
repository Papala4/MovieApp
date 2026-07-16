plugins {
    alias(libs.plugins.movieapp.android.application)
    alias(libs.plugins.movieapp.android.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.space.movieapp"
}

dependencies{
    implementation(project(":core:ui"))
    implementation(project(":core:network"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":feature:favorite:presentation"))
    implementation(project(":feature:moviedetails:presentation"))
    implementation(project(":feature:moviedetails:data"))
    implementation(project(":feature:home:data"))
    implementation(project(":feature:home:presentation"))
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.bundles.lifecycle)
    implementation(libs.bundles.navigation3)
}