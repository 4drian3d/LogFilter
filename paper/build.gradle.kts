plugins {
    id("net.minecrell.plugin-yml.bukkit") version "0.5.2"
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly(project(":logfilter-common"))
    compileOnly("io.papermc.paper:paper-api:1.19.2-R0.1-SNAPSHOT")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks.compileJava {
    options.encoding = Charsets.UTF_8.name()

    options.release.set(17)
}

bukkit {
    main = "me.adrianed.logfilter.paper.LogFilterPaper"
    description = project.description as String
    name = "LogFilter"
    version = project.version as String

    apiVersion = "1.13"
    
    author = "4drian3d"
}
