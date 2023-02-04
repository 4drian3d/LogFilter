import org.spongepowered.gradle.plugin.config.PluginLoaders

plugins {
    id("logfilter.shadow.java")
    alias(libs.plugins.sponge.gradle)
}

repositories {
    maven("https://repo.spongepowered.org/repository/maven-public/")
}

dependencies {
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

java.toolchain.languageVersion.set(JavaLanguageVersion.of(8))