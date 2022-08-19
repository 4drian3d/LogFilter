plugins {
    id("net.kyori.blossom") version "1.3.1"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

val url = "https://github.com/4drian3d/LogFilter"
val id = "logfilter"

blossom {
    replaceTokenIn("src/main/java/me/adrianed/logfilter/common/Constants.java")
    replaceToken("{name}", rootProject.name)
    replaceToken("{id}", id)
    replaceToken("{version}", version)
    replaceToken("{description}", description)
    replaceToken("{url}", url)
}

dependencies {
    shadow("org.spongepowered:configurate-hocon:4.1.2")
}

tasks {
    shadowJar {
        relocate("org.spongepowered", "me.adrianed.logfilter.libs.sponge")
        relocate("io.leangen.geantyref", "me.adrianed.logfilter.libs.geantyref")
    }
}