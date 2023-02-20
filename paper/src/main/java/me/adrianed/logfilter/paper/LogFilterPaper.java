package me.adrianed.logfilter.paper;

import java.nio.file.Path;

import org.apache.logging.log4j.Logger;

import me.adrianed.logfilter.common.configuration.Configuration;
import me.adrianed.logfilter.common.configuration.Loader;
import me.adrianed.logfilter.common.filter.Filters;

import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class LogFilterPaper extends JavaPlugin {
    private final Path pluginPath;

    LogFilterPaper(Path path) {
        this.pluginPath = path;
    }
    @Override
    public void onLoad() {

        @SuppressWarnings("deprecation")
        final Logger logger = getLog4JLogger();
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
