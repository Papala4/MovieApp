pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

includeAllModules("core", "features")

fun includeAllModules(vararg groupDirs: String) {
    val rootDir = settings.rootDir
    groupDirs.forEach { group ->
        File(rootDir, group).walkTopDown()
            .filter { it.isDirectory && File(it, "build.gradle.kts").exists() }
            .forEach {
                val relativePath = it.relativeTo(rootDir).path.replace(File.separator, ":")
                include(":$relativePath")
            }
    }
}

include("app")
