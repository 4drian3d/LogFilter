package io.github._4drian3d.logfilter.common.configuration;

import org.apache.logging.log4j.spi.StandardLevel;

import java.util.List;

public interface Configuration {
    List<String> blockedStrings();

    boolean regexMode();

    StandardLevel minimumLogLevel();
}
