plugins {
    alias(libs.plugins.movieapp.android.feature)
}

android {
    namespace = "com.space.moviedetails.domain"
}

dependencies {
    implementation(libs.core.ktx)
    implementation(project(":core:network"))
}
