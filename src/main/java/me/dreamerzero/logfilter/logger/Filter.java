package me.dreamerzero.logfilter.logger;

import java.util.regex.Pattern;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.filter.AbstractFilter;
import org.apache.logging.log4j.message.Message;

import me.dreamerzero.logfilter.config.Configuration;

public final class Filter extends AbstractFilter {
    private final Configuration config;
    public Filter(Configuration config){
        this.config = config;
    }

    public void registerFilter(){
        ((Logger)LogManager.getRootLogger()).addFilter(this);
    }

    @Override
    public Result filter(final LogEvent event){
        return event == null ? Result.NEUTRAL : logResult(event.getMessage().getFormattedMessage());
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final Message msg,
                        final Throwable t) {
        Result result = t != null ? logResult(t.getMessage()) : Result.NEUTRAL;
        if(msg != null) {
            if(result == Result.DENY) return result;
            return logResult(msg.getFormattedMessage());
        }
        return Result.NEUTRAL;
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final String msg,
                        final Object... params) {
        return logResult(msg);
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final Object msg,
                        final Throwable t) {
        Result result = t != null ? logResult(t.getMessage()) : Result.NEUTRAL;
        if(msg != null) {
            if(result == Result.DENY) return result;
            return logResult(msg.toString());
        }
        return Result.NEUTRAL;
    }

    private Result logResult(String string){
        if(string == null) return Result.NEUTRAL;
        return config.useRegex() ? regexCheck(string) : containsCheck(string);
    }

    private Result regexCheck(String string){
        for(String regex : config.getBlockedString()){
            if(Pattern.compile(regex).matcher(string).find())
                return Result.DENY;
        }
        return Result.NEUTRAL;
    }

    private Result containsCheck(String string){
        for(String check : config.getBlockedString()){
            if(string.contains(check)) return Result.DENY;
        }
        return Result.NEUTRAL;
    }
}
