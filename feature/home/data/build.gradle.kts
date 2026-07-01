plugins {
    alias(libs.plugins.movieapp.android.feature)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.space.mylibrary"
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.paging.common)
    implementation(project(":core:network"))
    implementation(project(":feature:home:domain"))
}
