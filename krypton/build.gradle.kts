repositories {
    maven("https://repo.kryptonmc.org/releases")
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
    compileOnly(project(":logfilter-common"))
    compileOnly("org.kryptonmc:krypton-api:0.66.1")
    annotationProcessor("org.kryptonmc:krypton-annotation-processor:0.66.1")
}

tasks.compileJava {
    options.encoding = Charsets.UTF_8.name()

    options.release.set(17)
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))