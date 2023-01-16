package me.adrianed.logfilter.bungee;

import me.adrianed.logfilter.common.configuration.Configuration;
import me.adrianed.logfilter.common.configuration.Loader;
import me.adrianed.logfilter.common.filter.Filters;
import net.md_5.bungee.api.plugin.Plugin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;

@SuppressWarnings("unused")
public class LogFilterBungee extends Plugin {
    @Override
    public void onEnable() {
        Path pluginPath = getDataFolder().toPath();

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