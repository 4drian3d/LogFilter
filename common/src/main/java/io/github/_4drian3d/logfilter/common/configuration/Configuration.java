package io.github._4drian3d.logfilter.common.configuration;

import java.util.List;

public interface Configuration {
    List<String> blockedStrings();

    boolean regexMode();
}
