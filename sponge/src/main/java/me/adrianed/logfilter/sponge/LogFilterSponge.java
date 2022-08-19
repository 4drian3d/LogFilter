package me.adrianed.logfilter.sponge;

import java.nio.file.Path;

import org.apache.logging.log4j.Logger;
import org.spongepowered.api.Server;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.lifecycle.StartedEngineEvent;
import org.spongepowered.plugin.builtin.jvm.Plugin;

import com.google.inject.Inject;

import me.adrianed.logfilter.common.configuration.Configuration;
import me.adrianed.logfilter.common.configuration.Loader;
import me.adrianed.logfilter.common.filter.Filters;

@Plugin("logfilter")
public class LogFilterSponge {
    private final Logger logger;
    @Inject
    @ConfigDir(sharedRoot = false)
    private Path path;

    @Inject
    public LogFilterSponge(Logger logger) {
        this.logger = logger;
    }

    @Listener
    public void onPreInit(StartedEngineEvent<Server> event) {
        logger.info("Loading filter");

        if (!Loader.loadFiles(path, logger)) {
            return;
        }

        Configuration config = Loader.loadConfig(path, logger);
        if (config == null) {
            return;
        }

        String filter = Filters.applyFilter(config);

        logger.info("Correctly loaded {} filter", filter);
    }
}
