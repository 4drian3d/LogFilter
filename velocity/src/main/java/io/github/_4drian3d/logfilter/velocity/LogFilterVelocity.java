package io.github._4drian3d.logfilter.velocity;

import java.nio.file.Path;

import com.google.inject.Inject;
import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;

import io.github._4drian3d.logfilter.common.Constants;
import io.github._4drian3d.logfilter.common.configuration.Configuration;
import io.github._4drian3d.logfilter.common.configuration.Loader;
import io.github._4drian3d.logfilter.common.filter.Filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Plugin(
    id = "logfilter",
    name = "LogFilter-Velocity",
    version = Constants.VERSION,
    description = "Filter messages sent to your Velocity, Sponge, and Paper consoles",
    url = "https://github.com/4drian3d/LogFilter",
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

    @Subscribe(order = PostOrder.FIRST)
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
