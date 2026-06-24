plugins {
    alias(libs.plugins.movieapp.android.feature)
}

android {
    namespace = "com.space.domain"
}

dependencies {
    implementation(libs.core.ktx)
}