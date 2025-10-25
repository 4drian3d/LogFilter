plugins {
    id("logfilter.shadow.java")
    id("xyz.jpenilla.run-paper")
    alias(libs.plugins.pluginyml.paper)
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
        jvmArgs("-Dcom.mojang.eula.agree=true")
        minecraftVersion("1.21.10")
    }
}

paper {
    name = "LogFilter"
    author = "4drian3d"
    main = "io.github._4drian3d.logfilter.paper.LogFilterPaper"
    bootstrapper = "io.github._4drian3d.logfilter.paper.LogBootstrap"
    loader = "io.github._4drian3d.logfilter.paper.LogLoader"
    hasOpenClassloader = false
    foliaSupported = true
    apiVersion = "1.21"
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(21))
