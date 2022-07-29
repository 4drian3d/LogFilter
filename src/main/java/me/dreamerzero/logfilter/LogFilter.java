package me.dreamerzero.logfilter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.inject.Inject;
import com.moandjiezana.toml.Toml;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;

import org.slf4j.Logger;

import me.dreamerzero.logfilter.config.Configuration;
import me.dreamerzero.logfilter.logger.CustomFilter;
import me.dreamerzero.logfilter.logger.PatternFilter;
import me.dreamerzero.logfilter.logger.StringFilter;
import me.dreamerzero.logfilter.utils.Constants;

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
        Toml toml = this.loadConfig();
        if (toml == null) {
          return;
        }
        Configuration config = new Configuration(toml);
        CustomFilter filter = config.useRegex()
            ? new PatternFilter(config)
            : new StringFilter(config);
        filter.registerFilter();
        logger.info("Correctly loaded {} filter", filter.getName());
    }

    private Toml loadConfig(){
        if (Files.notExists(pluginPath)) {
            try {
                Files.createDirectory(pluginPath);
            } catch (IOException e) {
                logger.error("Unable to create plugin directory", e);
                return null;
            }
        }
        Path configPath = pluginPath.resolve("config.toml");
        if (Files.notExists(configPath)) {
            try (InputStream in = getClass().getClassLoader().getResourceAsStream("config.toml")) {
                Files.copy(in, configPath);
            } catch (IOException e) {
                logger.error("Unable to create plugin configuration", e);
                return null;
            }
        }

        return new Toml().read(configPath.toFile());
    }
}
