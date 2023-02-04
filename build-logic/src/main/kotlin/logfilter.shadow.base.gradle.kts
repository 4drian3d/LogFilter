import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("com.github.johnrengelman.shadow")
}

fun String.firstUppercase(): String {
    val st = this.substring(10)

    val char = st[0]
    return char.toUpperCase() + st.substring(1)
}

dependencies {
    shadow(project(":logfilter-common"))
    add("compileOnly", "org.apache.logging.log4j:log4j-core:2.18.0")
}

tasks{
    withType<ShadowJar> {
        configurations = listOf(project.configurations.shadow.get())
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        archiveFileName.set("LogFilter-${project.name.firstUppercase()}-${project.version}.jar")
    }

    named("build") {
        dependsOn("shadowJar")
    }
}