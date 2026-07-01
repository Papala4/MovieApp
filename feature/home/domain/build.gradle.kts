plugins {
    alias(libs.plugins.movieapp.android.feature)
}

android {
    namespace = "com.space.domain"
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.paging.common)
    implementation(project(":core:network"))
}
