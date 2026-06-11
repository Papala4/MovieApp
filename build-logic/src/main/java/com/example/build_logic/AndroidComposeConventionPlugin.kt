package com.example.build_logic

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.api.artifacts.VersionCatalogsExtension

class AndroidComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {

        pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

        val androidExtension =
            extensions.getByType<BaseAppModuleExtension>()

        androidExtension.buildFeatures {
            compose = true
        }

        val libs =
            extensions.getByType<VersionCatalogsExtension>().named("libs")


        dependencies {
            val bom = libs.findLibrary("compose-bom").get()
            add("implementation", platform(bom))
            add("androidTestImplementation", platform(bom))

            add("implementation", libs.findBundle("compose-ui").get())
            add("debugImplementation", libs.findLibrary("compose-ui-tooling").get())
        }
    }
}