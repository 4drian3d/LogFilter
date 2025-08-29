plugins {
    id("logfilter.shadow.configurate")
    id("xyz.jpenilla.run-velocity")
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