plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.plugin.shadow)
}

repositories {
    gradlePluginPortal()
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}