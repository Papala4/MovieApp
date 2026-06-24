plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.space.presentation"
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.viewmodel.compose)
}