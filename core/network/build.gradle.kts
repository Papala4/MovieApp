import java.util.Properties

plugins {
    alias(libs.plugins.movieapp.android.library)
}

val localProperties = Properties().apply {
    load(rootProject.file("local.properties").inputStream())
}

android {
    namespace = "com.space.network"

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"${localProperties["BASE_URL"]}\"")
            buildConfigField("String", "AUTH_TOKEN", "\"${localProperties["AUTH_TOKEN"]}\"")
            buildConfigField("String", "IMAGE_BASE_URL", "\"${localProperties["IMAGE_BASE_URL"]}\"")
        }
        release {
            buildConfigField("String", "BASE_URL", "\"${localProperties["BASE_URL"]}\"")
            buildConfigField("String", "AUTH_TOKEN", "\"${localProperties["AUTH_TOKEN"]}\"")
            buildConfigField("String", "IMAGE_BASE_URL", "\"${localProperties["IMAGE_BASE_URL"]}\"")
        }
    }
}


dependencies {
    implementation(libs.core.ktx)
    implementation(libs.retrofit.core)
}
