plugins {
    id("net.minecrell.plugin-yml.bukkit") version "0.5.2"
    id("logfilter.shadow.java")
    alias(libs.plugins.runpaper)
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
            attributes("Automatic-Module-Name" to "me.adrianed.logfilter.paper")
        }
    }
    runServer {
        minecraftVersion("1.19.3")
    }
}

bukkit {
    main = "me.adrianed.logfilter.paper.LogFilterPaper"
    description = project.description as String
    name = "LogFilter"
    version = project.version as String
    apiVersion = "1.13"
    author = "4drian3d"
}

java.sourceCompatibility = JavaVersion.VERSION_1_8
java.targetCompatibility = JavaVersion.VERSION_1_8
