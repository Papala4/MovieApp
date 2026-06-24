plugins {
    alias(libs.plugins.movieapp.android.feature)
}

android {
    namespace = "com.space.data"
}

dependencies {
    implementation(libs.core.ktx)
}