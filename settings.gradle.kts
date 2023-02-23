rootProject.name = "logfilter-parent"

arrayOf("common", "bukkit", "paper", "velocity", "sponge", "krypton", "waterfall").forEach {
    include("logfilter-$it")
    project(":logfilter-$it").projectDir = file(it)
}

pluginManagement {
    @Suppress("UnstableApiUsage")
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}
