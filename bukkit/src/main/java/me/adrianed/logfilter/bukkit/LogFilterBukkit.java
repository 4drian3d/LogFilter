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
        Path pluginPath = getDataFolder().toPath();

        // legacy support, yay
        Logger logger = LogManager.getLogger("LogFilter");
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
