plugins {
    alias(libs.plugins.movieapp.android.feature)
}

android {
    namespace = "com.space.home.presentation"
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.koin.androidx.compose)
    implementation(libs.paging.common)
    implementation(libs.paging.compose)
    implementation(project(":core:network"))
    implementation(project(":core:presentation"))
    implementation(project(":feature:home:domain"))
}
