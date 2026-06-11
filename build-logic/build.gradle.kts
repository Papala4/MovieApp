plugins {
    `kotlin-dsl`
}

group = "com.movieapp.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}


dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {

        register("androidApplication") {
            id = "movieapp.android.application"
            implementationClass = "com.example.build_logic.AndroidApplicationConventionPlugin"
        }

        register("androidLibrary") {
            id = "movieapp.android.library"
            implementationClass = "com.example.build_logic.AndroidLibraryConventionPlugin"
        }

        register("androidCompose") {
            id = "movieapp.android.compose"
            implementationClass = "com.example.build_logic.AndroidComposeConventionPlugin"
        }

        register("androidKoin") {
            id = "movieapp.android.koin"
            implementationClass = "com.example.build_logic.AndroidKoinConventionPlugin"
        }

        register("androidNetworking") {
            id = "movieapp.android.networking"
            implementationClass = "com.example.build_logic.AndroidNetworkingConventionPlugin"
        }


        register("androidFeature") {
            id = "movieapp.android.feature"
            implementationClass = "com.example.build_logic.AndroidFeatureConventionPlugin"
        }

        register("jvmLibrary") {
            id = "movieapp.jvm.library"
            implementationClass = "com.example.build_logic.JvmLibraryConventionPlugin"
        }
    }
}
