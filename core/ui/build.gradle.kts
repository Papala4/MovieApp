plugins {
    alias(libs.plugins.movieapp.android.library)
    alias(libs.plugins.movieapp.android.compose)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.space.ui"
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.core.ktx)
    implementation(libs.material)
    implementation(libs.coil.compose)
}