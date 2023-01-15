plugins {
    alias(libs.plugins.blossom)
    alias(libs.plugins.extramodule)
}

val url :String by project
val id :String by project

blossom {
    replaceTokenIn("src/main/java/me/adrianed/logfilter/common/Constants.java")
    replaceToken("{name}", rootProject.name)
    replaceToken("{id}", id)
    replaceToken("{version}", version)
    replaceToken("{description}", description)
    replaceToken("{url}", url)
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(libs.configurate)
    compileOnly(libs.log4j2)
}

extraJavaModuleInfo {
    automaticModule("io.leangen.geantyref:geantyref", "io.leangen.geantyref")
}

tasks{
    compileJava {
        options.encoding = Charsets.UTF_8.name()
    }
    jar {
        manifest {
            attributes("Automatic-Module-Name" to "me.adrianed.logfilter.common")
        }
    }
}

java.sourceCompatibility = JavaVersion.VERSION_1_8
java.targetCompatibility = JavaVersion.VERSION_1_8

