package me.adrianed.logfilter.logger;

import java.util.regex.Pattern;
import java.util.stream.Collectors;

import me.adrianed.logfilter.configuration.Configuration;

public final class PatternFilter extends CustomFilter {
    private final Pattern[] patterns;

    public PatternFilter(Configuration config) {
        this.patterns = config.getBlockedString()
            .stream()
            .map(Pattern::compile)
            // Collect to set, does not allow duplicated patterns
            .collect(Collectors.toSet())
            // Convert to an array, maximun performance
            .toArray(new Pattern[0]);
    }

    @Override
    protected Result logResult(final String string) {
        for (int i = 0; i < patterns.length; i++) {
            if (patterns[i].matcher(string).find()) {
                return Result.DENY;
            }
        }
        return Result.NEUTRAL;
    }

    @Override
    public String getName() {
      return "Pattern";
    }
}
