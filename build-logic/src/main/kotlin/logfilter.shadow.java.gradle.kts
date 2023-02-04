plugins {
    java
    id("logfilter.shadow.base")
}

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name()
    }
    build {
        dependsOn(shadowJar)
    }
}

