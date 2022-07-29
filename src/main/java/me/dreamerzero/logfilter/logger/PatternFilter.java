package me.dreamerzero.logfilter.logger;

import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import me.dreamerzero.logfilter.config.Configuration;

public class PatternFilter extends CustomFilter {
    private final Set<Pattern> patterns;

    public PatternFilter(Configuration config) {
        this.patterns = config.getBlockedString().stream()
            .map(Pattern::compile)
            .collect(Collectors.toSet());
    }

    @Override
    protected Result logResult(String string) {
        for (final Pattern pattern : patterns){
            if (pattern.matcher(string).find())
                return Result.DENY;
        }
        return Result.NEUTRAL;
    }

    @Override
    public String getName() {
      return "Pattern";
    }
}
