package com.space.build_logic

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {

        pluginManager.apply("com.android.application")
        pluginManager.apply("org.jetbrains.kotlin.android")
        pluginManager.apply("org.jetbrains.kotlin.plugin.serialization")

        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

        extensions.configure<ApplicationExtension> {
            compileSdk = 34

            defaultConfig {
                minSdk = 24
                targetSdk = 35
                versionCode = 1
                versionName = "1.0.0"
                testInstrumentationRunner =
                    "androidx.test.runner.AndroidJUnitRunner"
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

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }
        }

        extensions.configure<KotlinAndroidProjectExtension> {
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_17)
            }
        }

        dependencies {
            "implementation"(libs.findBundle("koin-compose").get())
            "implementation"(libs.findBundle("networking").get())
            "testImplementation"(libs.findLibrary("koin-test").get())
        }
    }
}