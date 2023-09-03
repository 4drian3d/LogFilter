package me.adrianed.logfilter.bukkit;

import java.nio.file.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.adrianed.logfilter.common.configuration.Configuration;
import me.adrianed.logfilter.common.configuration.Loader;
import me.adrianed.logfilter.common.filter.Filters;

import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class LogFilterBukkit extends JavaPlugin {
    @Override
    public void onLoad() {
        final Path pluginPath = getDataFolder().toPath();

        // legacy support, yay
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
        logger.warn("This version of LogFilter is deprecated and will be removed in the future. Consider using Paper together with LogFilterPaper");
    }
}
