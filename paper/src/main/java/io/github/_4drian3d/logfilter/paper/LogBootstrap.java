package io.github._4drian3d.logfilter.paper;

import io.github._4drian3d.logfilter.common.configuration.Configuration;
import io.github._4drian3d.logfilter.common.configuration.Loader;
import io.github._4drian3d.logfilter.common.filter.Filters;
import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

@SuppressWarnings({"UnstableApiUsage", "unused"})
public class LogBootstrap implements PluginBootstrap {
    @Override
    public void bootstrap(@NotNull BootstrapContext context) {
        final Path pluginPath = context.getDataDirectory();
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
    }
}
