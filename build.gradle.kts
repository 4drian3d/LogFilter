plugins {
    alias(libs.plugins.runpaper) apply false
}

subprojects {
    repositories {
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}