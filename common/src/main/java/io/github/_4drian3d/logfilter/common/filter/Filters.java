package io.github._4drian3d.logfilter.common.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import io.github._4drian3d.logfilter.common.configuration.Configuration;

public final class Filters {
    private Filters() {}

    public static String applyFilter(Configuration configuration) {
        Logger rootLogger = (Logger)LogManager.getRootLogger();
        CustomFilter filter;
        if (configuration.regexMode()) {
            filter = new PatternFilter(configuration);
        } else {
            filter = new StringFilter(configuration);
        }

        rootLogger.addFilter(filter);
        return filter.getName();
    }
}
