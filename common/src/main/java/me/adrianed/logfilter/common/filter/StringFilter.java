package me.adrianed.logfilter.common.filter;

import me.adrianed.logfilter.common.configuration.Configuration;

public final class StringFilter extends CustomFilter {
    private final String[] blockedStrings;

    public StringFilter(Configuration config) {
        this.blockedStrings = config.blockedStrings()
            .toArray(new String[0]);
    }

    @Override
    protected Result logResult(final String string){
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
