package me.dreamerzero.logfilter.configuration;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import com.google.common.reflect.TypeToken;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

public interface Configuration {
    List<String> getBlockedString();

    boolean useRegex();

    static Configuration load(final Path path) throws IOException, ObjectMappingException {
        final HoconConfigurationLoader loader = HoconConfigurationLoader.builder()
            .setPath(path)
            .setDefaultOptions(options -> options.setHeader("LogFilter | by 4drian3d"))
            .build();

        final ConfigurationNode node = loader.load();
        final List<String> patterns = node.getNode("blocked-strings")
            .getList(TypeToken.of(String.class), Collections.emptyList());
        final boolean useRegex = node.getNode("use-regex").getBoolean(false);

        return new Configuration() {
            @Override
            public List<String> getBlockedString() {
                return patterns;
            }

            @Override
            public boolean useRegex() {
                return useRegex;
            }
        };
    }
}