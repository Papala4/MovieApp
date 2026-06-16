package com.space.build_logic

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.api.artifacts.VersionCatalogsExtension

class AndroidComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {

        pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

        extensions.findByType(LibraryExtension::class.java)?.let { libExt ->
            libExt.buildFeatures { compose = true }
        }
        extensions.findByType(ApplicationExtension::class.java)?.let { appExt ->
            appExt.buildFeatures { compose = true }
        }

        dependencies {
            val bom = libs.findLibrary("compose-bom").get()
            add("implementation", platform(bom))
            add("androidTestImplementation", platform(bom))

            add("implementation", libs.findBundle("compose-ui").get())
            add("debugImplementation", libs.findLibrary("compose-ui-tooling").get())
        }
    }
}