plugins {
    id("logfilter.shadow.configurate")
    alias(libs.plugins.pluginyml.bungee)
    id("xyz.jpenilla.run-waterfall")
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly(libs.waterfall)
}

tasks{
    compileJava {
        options.encoding = Charsets.UTF_8.name()
    }
    jar {
        manifest {
            attributes("Automatic-Module-Name" to "io.github._4drian3d.logfilter.bungee")
        }
    }
    runWaterfall {
        waterfallVersion("1.19")
    }
}

bungee {
    main = "io.github._4drian3d.logfilter.bungee.LogFilterBungee"
    description = project.description as String
    name = "LogFilter"
    version = project.version as String
    author = "4drian3d"
}

java.sourceCompatibility = JavaVersion.VERSION_1_8
java.targetCompatibility = JavaVersion.VERSION_1_8
