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
        relocate("org.spongepowered", "io.github._4drian3d.logfilter.libs.sponge")
        relocate("io.leangen.geantyref", "io.github._4drian3d.logfilter.libs.geantyref")
        relocate("com.typesafe.config", "io.github._4drian3d.logfilter.libs.config")
    }
}