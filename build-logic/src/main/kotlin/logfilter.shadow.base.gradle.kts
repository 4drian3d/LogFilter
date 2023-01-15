import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("com.github.johnrengelman.shadow")
}


fun String.firstUppercase(): String {
    val st = this.substring(10)

    val char = st[0]
    return char.toUpperCase() + st.substring(1)
}

repositories {
    mavenCentral()
}

dependencies {
    shadow(project(":logfilter-common"))
    shadow("org.spongepowered:configurate-hocon:4.1.2")
    add("compileOnly", "org.apache.logging.log4j:log4j-core:2.18.0")
}

tasks{
    withType<ShadowJar> {
        configurations = listOf(project.configurations.shadow.get())
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        archiveFileName.set("LogFilter-${project.name.firstUppercase()}-${project.version}.jar")

        relocate("org.spongepowered", "me.adrianed.logfilter.libs.sponge")
        relocate("io.leangen.geantyref", "me.adrianed.logfilter.libs.geantyref")
        relocate("com.typesafe.config", "me.adrianed.logfilter.libs.config")
    }

    named("build") {
        dependsOn("shadowJar")
    }
}