plugins {
    id("logfilter.shadow.java")
    id("xyz.jpenilla.run-paper")
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly(libs.paper)
}

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(21)
    }
    jar {
        manifest {
            attributes("Automatic-Module-Name" to "io.github._4drian3d.logfilter.paper")
        }
    }
    runServer {
        minecraftVersion("1.21.8")
    }
    processResources {
        filesMatching("paper-plugin.yml") {
            expand("version" to project.version)
        }
    }
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(21))
