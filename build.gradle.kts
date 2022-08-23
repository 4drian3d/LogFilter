plugins {
    java
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    shadow(project(":logfilter-common", "shadow"))
    shadow(project(":logfilter-sponge"))
    shadow(project(":logfilter-velocity"))
}

allprojects {
    apply<JavaPlugin>()

    repositories {
        mavenCentral()
    }

    dependencies {
        compileOnly("org.apache.logging.log4j:log4j-core:2.18.0")
    }

    tasks.compileJava {
        options.encoding = Charsets.UTF_8.name()

        options.release.set(11)
    }

    java.toolchain.languageVersion.set(JavaLanguageVersion.of(11))
}


tasks {
    shadowJar {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        archiveFileName.set("LogFilter-$version.jar")
        configurations = listOf(project.configurations.shadow.get())

        // Bypass Paper/Krypton java 17
        arrayOf("paper", "krypton").forEach {
            val buildTask = project(":logfilter-$it").tasks.named("jar")
            dependsOn(buildTask)
            
            from(zipTree(buildTask.map {out -> out.outputs.files.singleFile}))
        }

        relocate("org.spongepowered", "me.adrianed.logfilter.libs.sponge")
        relocate("io.leangen.geantyref", "me.adrianed.logfilter.libs.geantyref")
        relocate("com.typesafe.config", "me.adrianed.logfilter.libs.config")
    }

    build {
        dependsOn(shadowJar)
    }
}