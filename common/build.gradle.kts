plugins {
    java
    alias(libs.plugins.idea.ext)
    alias(libs.plugins.blossom)
    alias(libs.plugins.extramodule)
}

dependencies {
    compileOnly(libs.configurate)
    compileOnly(libs.log4j2)
}

extraJavaModuleInfo {
    automaticModule("io.leangen.geantyref:geantyref", "io.leangen.geantyref")
}

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name()
    }
    jar {
        manifest {
            attributes("Automatic-Module-Name" to "io.github._4drian3d.logfilter.common")
        }
    }
}

java.sourceCompatibility = JavaVersion.VERSION_1_8
java.targetCompatibility = JavaVersion.VERSION_1_8

sourceSets {
    main {
        blossom {
            javaSources {
                property("version", project.version.toString())
            }
        }
    }
}
