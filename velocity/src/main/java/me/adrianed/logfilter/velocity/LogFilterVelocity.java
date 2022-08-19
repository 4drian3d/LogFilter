package me.adrianed.logfilter.velocity;

import java.nio.file.Path;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;

import me.adrianed.logfilter.common.Constants;
import me.adrianed.logfilter.common.configuration.Configuration;
import me.adrianed.logfilter.common.configuration.Loader;
import me.adrianed.logfilter.common.filter.Filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Plugin(
    id = Constants.ID,
    name = Constants.NAME,
    version = Constants.VERSION,
    description = Constants.DESCRIPTION,
    url = Constants.URL,
    authors = {
        "4drian3d"
    }
)
public final class LogFilterVelocity {
    private final Path pluginPath;
    private final Logger logger;

    @Inject
    public LogFilterVelocity(@DataDirectory Path pluginPath){
        this.pluginPath = pluginPath;
        this.logger = LogManager.getLogger("logfilter");
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event){
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
