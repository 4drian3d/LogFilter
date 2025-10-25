package io.github._4drian3d.logfilter.common.filter;

import io.github._4drian3d.logfilter.common.configuration.Configuration;
import org.apache.logging.log4j.Level;

public final class StringFilter extends CustomFilter {
  private final String[] blockedStrings;

  public StringFilter(Configuration config) {
    super(config.minimumLogLevel().intLevel());
    this.blockedStrings = config.blockedStrings()
        .toArray(new String[0]);
  }

  @Override
  protected Result logResult(final String string, Level level) {
    if (level.intLevel() > minimumLogLevelSupported) {
      return Result.DENY;
    }
    for (int i = 0; i < blockedStrings.length; i++) {
      if (string.contains(blockedStrings[i])) {
        return Result.DENY;
      }
    }
    return Result.NEUTRAL;
  }

  @Override
  public String getName() {
    return "Regular";
  }
}
