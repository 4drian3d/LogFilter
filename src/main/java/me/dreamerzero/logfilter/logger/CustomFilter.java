package me.dreamerzero.logfilter.logger;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.filter.AbstractFilter;
import org.apache.logging.log4j.message.Message;

public abstract class CustomFilter extends AbstractFilter {
    protected CustomFilter(){}

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
        if (msg != null) {
            if (result == Result.DENY) return result;
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
        if (msg != null) {
            if(result == Result.DENY) return result;
            return logResult(msg.toString());
        }
        return Result.NEUTRAL;
    }

    protected abstract Result logResult(String string);

    public abstract String getName();
}
