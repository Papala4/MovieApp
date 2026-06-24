plugins {
    alias(libs.plugins.movieapp.android.feature)
}

android {
    namespace = "com.space.presentation"
}

dependencies {
    implementation(libs.core.ktx)
}