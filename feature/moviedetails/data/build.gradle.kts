plugins {
    alias(libs.plugins.movieapp.android.feature)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.space.moviedetails.data"
}

dependencies {
    implementation(libs.core.ktx)
    implementation(project(":core:network"))
    implementation(project(":feature:moviedetails:domain"))
}
