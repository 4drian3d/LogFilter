plugins {
    id("logfilter.shadow.java")
}

repositories {
    maven("https://repo.kryptonmc.org/releases")
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
    implementation(project(":logfilter-common"))
    compileOnly(project(":logfilter-common"))
    compileOnly("org.kryptonmc:krypton-api:0.66.3")
    annotationProcessor("org.kryptonmc:krypton-annotation-processor:0.66.3")
}

tasks.compileJava {
    options.encoding = Charsets.UTF_8.name()
    options.release.set(17)
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))
