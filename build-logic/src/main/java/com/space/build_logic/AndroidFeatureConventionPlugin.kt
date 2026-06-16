package com.space.build_logic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.project

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("movieapp.android.library")
                apply("movieapp.android.compose")
                apply("movieapp.android.koin")
            }

            val libs  = extensions.getByType<VersionCatalogsExtension>()
                .named("libs")

            dependencies {
                "implementation"(libs.findBundle("lifecycle").get())
                "implementation"(libs.findLibrary("navigation-compose").get())
                "implementation"(libs.findLibrary("coroutines-android").get())
                "implementation"(libs.findLibrary("timber").get())
                "implementation"(project(":core:ui"))
                "testImplementation"(libs.findBundle("testing").get())
            }
        }
    }
}
