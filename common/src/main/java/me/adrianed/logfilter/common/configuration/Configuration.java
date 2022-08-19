package me.adrianed.logfilter.common.configuration;

import java.util.List;

public interface Configuration {
    List<String> blockedStrings();

    boolean regexMode();
}
