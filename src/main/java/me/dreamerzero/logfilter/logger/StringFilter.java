package me.dreamerzero.logfilter.logger;

import java.util.List;

import me.dreamerzero.logfilter.config.Configuration;

public class StringFilter extends CustomFilter {
    private final List<String> blockedStrings;

    public StringFilter(Configuration config) {
        this.blockedStrings = config.getBlockedString();
    }

    @Override
    protected Result logResult(String string){
        for (final String check : this.blockedStrings){
            if (string.contains(check)) return Result.DENY;
        }
        return Result.NEUTRAL;
    }

    @Override
    public String getName() {
      return "Regular";
    }
}
