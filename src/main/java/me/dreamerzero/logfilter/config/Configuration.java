package me.dreamerzero.logfilter.config;

import java.util.List;

import com.moandjiezana.toml.Toml;

public final class Configuration {
    private List<String> strings;
    private boolean useRegex;

    public Configuration(Toml toml){
        this.useRegex = toml.getBoolean("use-regex", false);
        this.strings = toml.getList("blocked-strings", List.<String>of());
    }

    public List<String> getBlockedString(){
        return this.strings;
    }

    public boolean useRegex(){
        return this.useRegex;
    }
}
