import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("logfilter.shadow.java")
}

val libs = extensions.getByType(org.gradle.accessors.dm.LibrariesForLibs::class)

dependencies {
    shadow( libs.configurate)
}

tasks {
    withType<ShadowJar> {
        relocate("org.spongepowered", "me.adrianed.logfilter.libs.sponge")
        relocate("io.leangen.geantyref", "me.adrianed.logfilter.libs.geantyref")
        relocate("com.typesafe.config", "me.adrianed.logfilter.libs.config")
    }
}