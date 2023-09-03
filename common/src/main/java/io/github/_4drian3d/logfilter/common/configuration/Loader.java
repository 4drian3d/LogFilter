package io.github._4drian3d.logfilter.common.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;
import org.spongepowered.configurate.serialize.SerializationException;

import io.leangen.geantyref.TypeToken;

import org.apache.logging.log4j.Logger;

public final class Loader {
    private Loader() {}
    private static final String CONFIG = "config.conf";

    public static boolean loadFiles(Path dataFolder, Logger logger) {
        if (Files.notExists(dataFolder)) {
            try {
                Files.createDirectory(dataFolder);
            } catch (IOException e) {
                logger.error("Unable to create plugin directory", e);
                return false;
            }
        }

        final Path configPath = dataFolder.resolve(CONFIG);
        if (Files.notExists(configPath)) {
            try (InputStream in = Configuration.class.getClassLoader().getResourceAsStream(CONFIG)) {
                Files.copy(in, configPath);
            } catch (IOException e) {
                logger.error("Unable to create plugin configuration", e);
                return false;
            }
        }
        return true;
    }

    public static Configuration loadConfig(Path dataFolder, Logger logger) {
        HoconConfigurationLoader loader = HoconConfigurationLoader.builder()
            .path(dataFolder.resolve(CONFIG))
            .build();

        ConfigurationNode node;
        try {
            node = loader.load();
        } catch (ConfigurateException e) {
            logger.error("Unable to load configuration");
            return null;
        }

        boolean regex = node.node("use-regex").getBoolean(false);
        List<String> blockedStrings;
        try {
            blockedStrings = node.node("blocked-strings")
                .getList(TypeToken.get(String.class), Collections.emptyList());
        } catch (SerializationException e) {
            logger.error("Unable to get blocked-strings");
            return null;
        }

        return new Configuration() {
            @Override
            public List<String> blockedStrings() {
                return blockedStrings;
            }

            @Override
            public boolean regexMode() {
                return regex;
            }
        };
    }

    
}
