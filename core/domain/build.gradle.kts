plugins {
    alias(libs.plugins.movieapp.android.library)
}

android {
    namespace = "com.space.core.domain"
}

dependencies {
    implementation(libs.coroutines.android)
}
