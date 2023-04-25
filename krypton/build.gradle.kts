plugins {
    id("logfilter.shadow.kotlin")
    kotlin("kapt") version "1.8.21"
}

repositories {
    maven("https://repo.kryptonmc.org/releases")
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
    compileOnly(libs.krypton.api)
    kapt(libs.krypton.processor)
}

tasks.compileJava {
    options.encoding = Charsets.UTF_8.name()
    options.release.set(17)
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}
