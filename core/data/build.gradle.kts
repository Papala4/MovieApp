plugins {
    alias(libs.plugins.movieapp.android.library)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.space.data"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)
    implementation(libs.coroutines.android)
}
