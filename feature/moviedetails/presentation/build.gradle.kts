plugins {
    alias(libs.plugins.movieapp.android.feature)
}

android {
    namespace = "com.space.moviedetails.presentation"
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.koin.androidx.compose)
    implementation(libs.coil.compose)
    implementation(project(":core:network"))
    implementation(project(":core:presentation"))
    implementation(project(":feature:moviedetails:domain"))
}
