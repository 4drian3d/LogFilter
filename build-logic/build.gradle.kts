plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.plugin.shadow)
    implementation(libs.plugin.kotlin)
    compileOnly(files(libs::class.java.protectionDomain.codeSource.location))
}

repositories {
    gradlePluginPortal()
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}