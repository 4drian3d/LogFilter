plugins {
    id("logfilter.shadow.configurate")
    alias(libs.plugins.pluginyml.bukkit)
    id("xyz.jpenilla.run-paper")
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly(libs.legacypaper)
}

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name()
    }
    jar {
        manifest {
            attributes("Automatic-Module-Name" to "io.github._4drian3d.logfilter.bukkit")
        }
    }
    runServer {
        minecraftVersion("1.20.2")
    }
}

bukkit {
    main = "io.github._4drian3d.logfilter.bukkit.LogFilterBukkit"
    description = project.description as String
    name = "LogFilter"
    version = project.version as String
    apiVersion = "1.13"
    author = "4drian3d"
    load = net.minecrell.pluginyml.bukkit.BukkitPluginDescription.PluginLoadOrder.STARTUP
    foliaSupported = true
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(11))
