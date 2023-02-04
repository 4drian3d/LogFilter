package me.adrianed.logfilter.krypton

import java.nio.file.Path

import org.apache.logging.log4j.Logger
import org.kryptonmc.api.event.Listener
import org.kryptonmc.api.event.server.ServerStartEvent
import org.kryptonmc.api.plugin.annotation.Plugin
import org.kryptonmc.api.plugin.annotation.DataFolder

import com.google.inject.Inject
import me.adrianed.logfilter.common.Constants
import me.adrianed.logfilter.common.configuration.Loader
import me.adrianed.logfilter.common.filter.Filters

@Plugin(
    id = Constants.ID,
    name = "LogFilter-Krypton",
    version = Constants.VERSION,
    description = Constants.DESCRIPTION,
    authors = ["4drian3d"]
)
class LogFilterKrypton @Inject constructor(
    private val logger: Logger,
    @DataFolder private val path: Path
) {

    @Listener
    fun onServerStart(event: ServerStartEvent) {
        logger.info("Loading filter")
        if (!Loader.loadFiles(path, logger)) {
            return
        }
        val config = Loader.loadConfig(path, logger) ?: return

        val filter = Filters.applyFilter(config)
        logger.info("Correctly loaded {} filter", filter)
    }
}