plugins {
    alias(libs.plugins.runpaper) apply false
}

tasks {
    delete {
        delete("jar")
    }
}
