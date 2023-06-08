import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("com.github.johnrengelman.shadow")
}

fun String.firstUppercase(): String {
    val st = this.substring(10)

    val char = st[0]
    return char.uppercaseChar() + st.substring(1)
}

val libs = extensions.getByType(org.gradle.accessors.dm.LibrariesForLibs::class)

dependencies {
    shadow(project(":logfilter-common"))
    add("compileOnly", libs.log4j2)
}

tasks {
    withType<ShadowJar> {
        configurations = listOf(project.configurations.shadow.get())
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        archiveFileName.set("LogFilter-${project.name.firstUppercase()}-${project.version}.jar")
    }

    named("build") {
        dependsOn("shadowJar")
    }
}