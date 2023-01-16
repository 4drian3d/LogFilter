import org.spongepowered.gradle.plugin.config.PluginLoaders

plugins {
    alias(libs.plugins.shadow)
    alias(libs.plugins.sponge.gradle)
}

repositories {
    maven("https://repo.spongepowered.org/repository/maven-public/")
}

dependencies {
    shadow(project(":logfilter-common"))
    compileOnly(libs.sponge)
}

sponge {
    apiVersion("8.0.0")
    loader {
        name(PluginLoaders.JAVA_PLAIN)
        version("1.0")
    }

    plugin("logfilter") {
        displayName("LogFilter")
        version(project.version as String)
        license("GPL-3")
        description(project.description as String)
        entrypoint("me.adrianed.logfilter.sponge.LogFilterSponge")
        links {
            homepage("https://github.com/4drian3d/LogFilter")
            source("https://github.com/4drian3d/LogFilter")
            issues("https://github.com/4drian3d/LogFilter/issues")
        }
        contributor("4drian3d") {
            description("Actual Developer")
        }
    }
}

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name()
    }
    build {
        dependsOn("shadowJar")
    }
    shadowJar {
        configurations = listOf(project.configurations.shadow.get())
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        archiveFileName.set("LogFilter-Sponge-${project.version}.jar")
    }
}

java.sourceCompatibility = JavaVersion.VERSION_1_8
java.targetCompatibility = JavaVersion.VERSION_1_8