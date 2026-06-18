package com.space.build_logic

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {

        pluginManager.apply("com.android.application")

        extensions.configure<ApplicationExtension> {
            target.configureKotlinAndroid(this)

            defaultConfig.apply {
                targetSdk = 37
                versionCode = 1
                versionName = "1.0.0"
            }

            buildTypes {
                getByName("debug") {
                    applicationIdSuffix = ".debug"
                    isDebuggable = true
                }

                getByName("release") {
                    isMinifyEnabled = true
                    isShrinkResources = true

                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
            }
        }

        dependencies {
            "implementation"(libs.findBundle("koin-compose").get())
            "implementation"(libs.findBundle("networking").get())
            "testImplementation"(libs.findLibrary("koin-test").get())
        }
    }
}