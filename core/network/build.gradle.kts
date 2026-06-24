plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.space.network"
}


dependencies {
    implementation(libs.core.ktx)
    implementation(libs.retrofit.core)
}
