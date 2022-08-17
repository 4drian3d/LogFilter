plugins {
    java
    id("net.kyori.blossom") version "1.3.1"
}

repositories {
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    compileOnly("com.velocitypowered:velocity-api:3.1.2-SNAPSHOT")
    compileOnly("org.apache.logging.log4j:log4j-core:2.17.1")
    // Thanks gradle cache
    compileOnly("org.spongepowered:configurate-hocon:3.7.3")
    annotationProcessor("com.velocitypowered:velocity-api:3.1.2-SNAPSHOT")
}

val url = "https://github.com/4drian3d/LogFilter"
val id = "logfilter"

java.toolchain.languageVersion.set(JavaLanguageVersion.of(11))

blossom {
    replaceTokenIn("src/main/java/me/adrianed/logfilter/utils/Constants.java")
    replaceToken("{name}", rootProject.name)
    replaceToken("{id}", id)
    replaceToken("{version}", version)
    replaceToken("{description}", description)
    replaceToken("{url}", url)
}


tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
    options.release.set(11)
}
