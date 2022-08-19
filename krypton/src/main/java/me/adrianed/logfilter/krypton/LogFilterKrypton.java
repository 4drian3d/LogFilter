package me.adrianed.logfilter.krypton;

import java.nio.file.Path;

import org.apache.logging.log4j.Logger;
import org.kryptonmc.api.event.Listener;
import org.kryptonmc.api.event.server.ServerStartEvent;
import org.kryptonmc.api.plugin.annotation.Plugin;

import com.google.inject.Inject;

import me.adrianed.logfilter.common.Constants;
import me.adrianed.logfilter.common.configuration.Configuration;
import me.adrianed.logfilter.common.configuration.Loader;
import me.adrianed.logfilter.common.filter.Filters;

@Plugin(
    id = Constants.ID,
    name = Constants.NAME,
    version = Constants.VERSION,
    description = Constants.DESCRIPTION,
    authors = {
        "4drian3d"
    }
)
public class LogFilterKrypton {
    @Inject
    private Logger logger;
    @Inject
    private Path pluginPath;

    @Listener
    public void onServerStart(ServerStartEvent event) {
        logger.info("Loading filter");

        if (!Loader.loadFiles(pluginPath, logger)) {
            return;
        }

        Configuration config = Loader.loadConfig(pluginPath, logger);
        if (config == null) {
            return;
        }

        String filter = Filters.applyFilter(config);

        logger.info("Correctly loaded {} filter", filter);
    }
}
