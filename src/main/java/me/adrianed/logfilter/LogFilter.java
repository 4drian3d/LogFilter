package me.adrianed.logfilter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;

import me.adrianed.logfilter.configuration.Configuration;
import me.adrianed.logfilter.logger.CustomFilter;
import me.adrianed.logfilter.logger.PatternFilter;
import me.adrianed.logfilter.logger.StringFilter;
import me.adrianed.logfilter.utils.Constants;

import org.slf4j.Logger;

import ninja.leaping.configurate.objectmapping.ObjectMappingException;

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
public final class LogFilter {
    private final Path pluginPath;
    private final Logger logger;

    @Inject
    public LogFilter(@DataDirectory Path pluginPath, Logger logger){
        this.pluginPath = pluginPath;
        this.logger = logger;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event){
        logger.info("Loading filter");


        if (Files.notExists(pluginPath)) {
            try {
                Files.createDirectory(pluginPath);
            } catch (IOException e) {
                logger.error("Unable to create plugin directory", e);
                return;
            }
        }

        final Path configPath = pluginPath.resolve("config.conf");
        if (Files.notExists(configPath)) {
            try (InputStream in = getClass().getClassLoader().getResourceAsStream("config.conf")) {
                Files.copy(in, configPath);
            } catch (IOException e) {
                logger.error("Unable to create plugin configuration", e);
                return;
            }
        }

        Configuration config;
        try {
            config = Configuration.load(configPath);
        } catch (IOException e) {
            logger.error("Cannot load config.conf file", e);
            return;
        } catch (ObjectMappingException e) {
            logger.error("Invalid configuration provided", e);
            return;
        }

        final CustomFilter filter = config.useRegex()
            ? new PatternFilter(config)
            : new StringFilter(config);
        filter.registerFilter();

        logger.info("Correctly loaded {} filter", filter.getName());
    }
}
