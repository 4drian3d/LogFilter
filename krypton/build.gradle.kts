plugins {
    id("logfilter.shadow.java")
}

repositories {
    maven("https://repo.kryptonmc.org/releases")
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
    compileOnly(libs.krypton.api)
    annotationProcessor(libs.krypton.processor)
}

tasks.compileJava {
    options.encoding = Charsets.UTF_8.name()
    options.release.set(17)
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))
