import org.spongepowered.gradle.plugin.config.PluginLoaders

plugins {
    id("logfilter.shadow.java")
    alias(libs.plugins.sponge.gradle)
}

sponge {
    apiVersion(libs.versions.sponge.api.get())
    loader {
        name(PluginLoaders.JAVA_PLAIN)
        version("1.0")
    }

    plugin("logfilter") {
        displayName("LogFilter")
        version(project.version as String)
        license("GPL-3")
        description(project.description as String)
        entrypoint("io.github._4drian3d.logfilter.sponge.LogFilterSponge")
        links {
            homepage("https://github.com/4drian3d/LogFilter")
            source("https://github.com/4drian3d/LogFilter")
            issues("https://github.com/4drian3d/LogFilter/issues")
        }
        contributor("4drian3d") {
            description("Developer")
        }
    }
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(11))