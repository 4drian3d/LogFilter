package io.github._4drian3d.logfilter.paper;

import io.papermc.paper.plugin.loader.PluginClasspathBuilder;
import io.papermc.paper.plugin.loader.PluginLoader;
import io.papermc.paper.plugin.loader.library.impl.MavenLibraryResolver;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.graph.Dependency;
import org.eclipse.aether.repository.RemoteRepository;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"UnstableApiUsage", "unused"})
public class LogLoader implements PluginLoader {
    @Override
    public void classloader(@NotNull PluginClasspathBuilder classpathBuilder) {
        final MavenLibraryResolver resolver = new MavenLibraryResolver();

        final RemoteRepository mavenCentral = new RemoteRepository
                .Builder("central-mirror", "default", "https://repo.papermc.io/repository/maven-public/")
                .build();
        final Dependency configurateHocon = new Dependency(
                new DefaultArtifact("org.spongepowered:configurate-hocon:4.2.0"),
                null
        );

        resolver.addRepository(mavenCentral);
        resolver.addDependency(configurateHocon);

        classpathBuilder.addLibrary(resolver);
    }
}
