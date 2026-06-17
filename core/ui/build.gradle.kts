plugins {
    id("movieapp.android.library")
    id("movieapp.android.compose")
}

android {
    namespace = "com.space.ui"
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.core.ktx)
    implementation(libs.material)
}