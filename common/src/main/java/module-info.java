@SuppressWarnings({"requires-automatic", "requires-transitive-automatic"})
module me.adrianed.logfilter.common {
    requires transitive org.apache.logging.log4j;
    requires transitive org.apache.logging.log4j.core;
    requires org.spongepowered.configurate;
    requires org.spongepowered.configurate.hocon;
    requires io.leangen.geantyref;

    exports me.adrianed.logfilter.common;
    exports me.adrianed.logfilter.common.configuration;
    exports me.adrianed.logfilter.common.filter;
}
