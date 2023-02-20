plugins {
    id("logfilter.shadow.configurate")
    id("xyz.jpenilla.run-velocity")
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly(libs.velocity)
    annotationProcessor(libs.velocity)
}

tasks {
    compileJava {
        options.release.set(11)
    }
    runVelocity {
        velocityVersion(libs.versions.velocity.get())
    }
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(11))