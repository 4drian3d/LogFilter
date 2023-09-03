rootProject.name = "logfilter-parent"

arrayOf("common", "bukkit", "paper", "velocity", "sponge", "waterfall").forEach {
    include("logfilter-$it")
    project(":logfilter-$it").projectDir = file(it)
}

pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
}
