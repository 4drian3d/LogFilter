rootProject.name = "logfilter-parent"

arrayOf("common", "paper", "velocity", "sponge", "krypton", "waterfall").forEach {
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
