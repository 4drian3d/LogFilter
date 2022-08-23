plugins {
    id("net.kyori.blossom") version "1.3.1"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("org.gradlex.extra-java-module-info") version "1.0"
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

extraJavaModuleInfo {
    // failOnMissingModuleInfo.set(false)
    /*module("io.leangen.geantyref:geantyref", "org.apache.commons.beanutils") {
        exports("org.apache.commons.beanutils")
        
        requires("org.apache.commons.logging")
        requires("java.sql")
        requires("java.desktop")
        
        // requiresTransitive(...)
        // requiresStatic(...)
    }
    module("commons-cli:commons-cli", "org.apache.commons.cli") {
        exports("org.apache.commons.cli")
    }
    module("commons-collections:commons-collections", "org.apache.commons.collections")*/
    automaticModule("io.leangen.geantyref:geantyref", "io.leangen.geantyref")
}
