import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("logfilter.shadow.java")
}

dependencies {
    shadow("org.spongepowered:configurate-hocon:4.1.2")
}

tasks {
    withType<ShadowJar> {
        relocate("org.spongepowered", "me.adrianed.logfilter.libs.sponge")
        relocate("io.leangen.geantyref", "me.adrianed.logfilter.libs.geantyref")
        relocate("com.typesafe.config", "me.adrianed.logfilter.libs.config")
    }
}