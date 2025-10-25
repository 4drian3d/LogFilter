@file:Suppress("UnstableApiUsage")
rootProject.name = "logfilter-parent"

arrayOf("common", "paper", "velocity", "sponge").forEach {
    include("logfilter-$it")
    project(":logfilter-$it").projectDir = file(it)
}

dependencyResolutionManagement {
    repositories {
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
