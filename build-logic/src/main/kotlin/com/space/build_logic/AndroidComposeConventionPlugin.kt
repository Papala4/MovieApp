package com.space.build_logic

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {

            when{
                pluginManager.hasPlugin("movieapp.android.application") -> {
                    configureAndroidCompose(
                        extensions.getByType<ApplicationExtension>()
                    )
                }
                pluginManager.hasPlugin("movieapp.android.library") -> {
                    configureAndroidCompose(
                        extensions.getByType<LibraryExtension>()
                    )
                }
                else -> error(
                    "Unsupported project type."
                )
            }
        }
    }
}