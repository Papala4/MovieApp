plugins {
    alias(libs.plugins.movieapp.android.feature)
}

android {
    namespace = "com.space.favorite.presentation"
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.koin.androidx.compose)
    implementation(project(":core:presentation"))
    implementation(project(":core:domain"))
}
