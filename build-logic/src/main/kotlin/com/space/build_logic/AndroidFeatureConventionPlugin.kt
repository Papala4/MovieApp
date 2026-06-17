package com.space.build_logic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("movieapp.android.library")
                apply("movieapp.android.compose")
            }

            dependencies {
                "implementation"(libs.findBundle("lifecycle").get())
                "implementation"(libs.findLibrary("navigation-compose").get())
                "implementation"(libs.findLibrary("coroutines-android").get())
                "implementation"(libs.findLibrary("timber").get())
//                "implementation"(project(":core:ui"))
                "testImplementation"(libs.findBundle("testing").get())
            }
        }
    }
}
