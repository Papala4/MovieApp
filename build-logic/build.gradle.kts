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
            implementationClass = "com.space.build_logic.AndroidApplicationConventionPlugin"
        }

        register("androidLibrary") {
            id = "movieapp.android.library"
            implementationClass = "com.space.build_logic.AndroidLibraryConventionPlugin"
        }

        register("androidCompose") {
            id = "movieapp.android.compose"
            implementationClass = "com.space.build_logic.AndroidComposeConventionPlugin"
        }


        register("androidNetworking") {
            id = "movieapp.android.networking"
            implementationClass = "com.space.build_logic.AndroidNetworkingConventionPlugin"
        }


        register("androidFeature") {
            id = "movieapp.android.feature"
            implementationClass = "com.space.build_logic.AndroidFeatureConventionPlugin"
        }

        register("jvmLibrary") {
            id = "movieapp.jvm.library"
            implementationClass = "com.space.build_logic.JvmLibraryConventionPlugin"
        }
    }
}
