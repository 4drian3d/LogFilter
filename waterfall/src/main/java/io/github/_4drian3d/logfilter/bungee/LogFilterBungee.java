package io.github._4drian3d.logfilter.bungee;

import io.github._4drian3d.logfilter.common.configuration.Configuration;
import io.github._4drian3d.logfilter.common.configuration.Loader;
import io.github._4drian3d.logfilter.common.filter.Filters;
import net.md_5.bungee.api.plugin.Plugin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;

@SuppressWarnings("unused")
public class LogFilterBungee extends Plugin {
    @Override
    public void onEnable() {
        final Path pluginPath = getDataFolder().toPath();

        final Logger logger = LogManager.getLogger("LogFilter");
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
        logger.warn("This version of LogFilter is deprecated and will be removed in the future. Consider using Velocity together with LogFilterVelocity");
    }
}