plugins {
    alias(libs.plugins.pluginyml.bungee)
    id("logfilter.shadow.java")
    alias(libs.plugins.runwaterfall)
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly(project(":logfilter-common"))
    compileOnly(libs.waterfall)
}

tasks{
    compileJava {
        options.encoding = Charsets.UTF_8.name()
    }
    jar {
        manifest {
            attributes("Automatic-Module-Name" to "me.adrianed.logfilter.bungee")
        }
    }
    runWaterfall {
        waterfallVersion("1.19")
    }
}

bungee {
    main = "me.adrianed.logfilter.bungee.LogFilterBungee"
    description = project.description as String
    name = "LogFilter"
    version = project.version as String
    author = "4drian3d"
}

java.sourceCompatibility = JavaVersion.VERSION_1_8
java.targetCompatibility = JavaVersion.VERSION_1_8
