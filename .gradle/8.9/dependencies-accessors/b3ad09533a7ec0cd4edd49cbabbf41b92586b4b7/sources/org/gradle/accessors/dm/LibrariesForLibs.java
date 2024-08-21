package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.MinimalExternalModuleDependency;
import org.gradle.plugin.use.PluginDependency;
import org.gradle.api.artifacts.ExternalModuleDependencyBundle;
import org.gradle.api.artifacts.MutableVersionConstraint;
import org.gradle.api.provider.Provider;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.internal.catalog.AbstractExternalDependencyFactory;
import org.gradle.api.internal.catalog.DefaultVersionCatalog;
import java.util.Map;
import org.gradle.api.internal.attributes.ImmutableAttributesFactory;
import org.gradle.api.internal.artifacts.dsl.CapabilityNotationParser;
import javax.inject.Inject;

/**
 * A catalog of dependencies accessible via the {@code libs} extension.
 */
@NonNullApi
public class LibrariesForLibs extends AbstractExternalDependencyFactory {

    private final AbstractExternalDependencyFactory owner = this;
    private final InfoLibraryAccessors laccForInfoLibraryAccessors = new InfoLibraryAccessors(owner);
    private final IoLibraryAccessors laccForIoLibraryAccessors = new IoLibraryAccessors(owner);
    private final NetLibraryAccessors laccForNetLibraryAccessors = new NetLibraryAccessors(owner);
    private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(providers, config);
    private final BundleAccessors baccForBundleAccessors = new BundleAccessors(objects, providers, config, attributesFactory, capabilityNotationParser);
    private final PluginAccessors paccForPluginAccessors = new PluginAccessors(providers, config);

    @Inject
    public LibrariesForLibs(DefaultVersionCatalog config, ProviderFactory providers, ObjectFactory objects, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) {
        super(config, providers, objects, attributesFactory, capabilityNotationParser);
    }

    /**
     * Group of libraries at <b>info</b>
     */
    public InfoLibraryAccessors getInfo() {
        return laccForInfoLibraryAccessors;
    }

    /**
     * Group of libraries at <b>io</b>
     */
    public IoLibraryAccessors getIo() {
        return laccForIoLibraryAccessors;
    }

    /**
     * Group of libraries at <b>net</b>
     */
    public NetLibraryAccessors getNet() {
        return laccForNetLibraryAccessors;
    }

    /**
     * Group of versions at <b>versions</b>
     */
    public VersionAccessors getVersions() {
        return vaccForVersionAccessors;
    }

    /**
     * Group of bundles at <b>bundles</b>
     */
    public BundleAccessors getBundles() {
        return baccForBundleAccessors;
    }

    /**
     * Group of plugins at <b>plugins</b>
     */
    public PluginAccessors getPlugins() {
        return paccForPluginAccessors;
    }

    public static class InfoLibraryAccessors extends SubDependencyFactory {
        private final InfoPicocliLibraryAccessors laccForInfoPicocliLibraryAccessors = new InfoPicocliLibraryAccessors(owner);

        public InfoLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>info.picocli</b>
         */
        public InfoPicocliLibraryAccessors getPicocli() {
            return laccForInfoPicocliLibraryAccessors;
        }

    }

    public static class InfoPicocliLibraryAccessors extends SubDependencyFactory {

        public InfoPicocliLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>picocli</b> with <b>info.picocli:picocli</b> coordinates and
         * with version reference <b>info.picocli.picocli</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getPicocli() {
            return create("info.picocli.picocli");
        }

    }

    public static class IoLibraryAccessors extends SubDependencyFactory {
        private final IoPapermcLibraryAccessors laccForIoPapermcLibraryAccessors = new IoPapermcLibraryAccessors(owner);

        public IoLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>io.papermc</b>
         */
        public IoPapermcLibraryAccessors getPapermc() {
            return laccForIoPapermcLibraryAccessors;
        }

    }

    public static class IoPapermcLibraryAccessors extends SubDependencyFactory {
        private final IoPapermcPaperLibraryAccessors laccForIoPapermcPaperLibraryAccessors = new IoPapermcPaperLibraryAccessors(owner);

        public IoPapermcLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>io.papermc.paper</b>
         */
        public IoPapermcPaperLibraryAccessors getPaper() {
            return laccForIoPapermcPaperLibraryAccessors;
        }

    }

    public static class IoPapermcPaperLibraryAccessors extends SubDependencyFactory {
        private final IoPapermcPaperPaperLibraryAccessors laccForIoPapermcPaperPaperLibraryAccessors = new IoPapermcPaperPaperLibraryAccessors(owner);

        public IoPapermcPaperLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>io.papermc.paper.paper</b>
         */
        public IoPapermcPaperPaperLibraryAccessors getPaper() {
            return laccForIoPapermcPaperPaperLibraryAccessors;
        }

    }

    public static class IoPapermcPaperPaperLibraryAccessors extends SubDependencyFactory {

        public IoPapermcPaperPaperLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>api</b> with <b>io.papermc.paper:paper-api</b> coordinates and
         * with version reference <b>io.papermc.paper.paper.api</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getApi() {
            return create("io.papermc.paper.paper.api");
        }

    }

    public static class NetLibraryAccessors extends SubDependencyFactory {
        private final NetKyoriLibraryAccessors laccForNetKyoriLibraryAccessors = new NetKyoriLibraryAccessors(owner);

        public NetLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>net.kyori</b>
         */
        public NetKyoriLibraryAccessors getKyori() {
            return laccForNetKyoriLibraryAccessors;
        }

    }

    public static class NetKyoriLibraryAccessors extends SubDependencyFactory {
        private final NetKyoriAdventureLibraryAccessors laccForNetKyoriAdventureLibraryAccessors = new NetKyoriAdventureLibraryAccessors(owner);

        public NetKyoriLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>net.kyori.adventure</b>
         */
        public NetKyoriAdventureLibraryAccessors getAdventure() {
            return laccForNetKyoriAdventureLibraryAccessors;
        }

    }

    public static class NetKyoriAdventureLibraryAccessors extends SubDependencyFactory {
        private final NetKyoriAdventureTextLibraryAccessors laccForNetKyoriAdventureTextLibraryAccessors = new NetKyoriAdventureTextLibraryAccessors(owner);

        public NetKyoriAdventureLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>net.kyori.adventure.text</b>
         */
        public NetKyoriAdventureTextLibraryAccessors getText() {
            return laccForNetKyoriAdventureTextLibraryAccessors;
        }

    }

    public static class NetKyoriAdventureTextLibraryAccessors extends SubDependencyFactory {
        private final NetKyoriAdventureTextSerializerLibraryAccessors laccForNetKyoriAdventureTextSerializerLibraryAccessors = new NetKyoriAdventureTextSerializerLibraryAccessors(owner);

        public NetKyoriAdventureTextLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>net.kyori.adventure.text.serializer</b>
         */
        public NetKyoriAdventureTextSerializerLibraryAccessors getSerializer() {
            return laccForNetKyoriAdventureTextSerializerLibraryAccessors;
        }

    }

    public static class NetKyoriAdventureTextSerializerLibraryAccessors extends SubDependencyFactory {

        public NetKyoriAdventureTextSerializerLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>legacy</b> with <b>net.kyori:adventure-text-serializer-legacy</b> coordinates and
         * with version reference <b>net.kyori.adventure.text.serializer.legacy</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getLegacy() {
            return create("net.kyori.adventure.text.serializer.legacy");
        }

    }

    public static class VersionAccessors extends VersionFactory  {

        private final InfoVersionAccessors vaccForInfoVersionAccessors = new InfoVersionAccessors(providers, config);
        private final IoVersionAccessors vaccForIoVersionAccessors = new IoVersionAccessors(providers, config);
        private final NetVersionAccessors vaccForNetVersionAccessors = new NetVersionAccessors(providers, config);
        public VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.info</b>
         */
        public InfoVersionAccessors getInfo() {
            return vaccForInfoVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.io</b>
         */
        public IoVersionAccessors getIo() {
            return vaccForIoVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.net</b>
         */
        public NetVersionAccessors getNet() {
            return vaccForNetVersionAccessors;
        }

    }

    public static class InfoVersionAccessors extends VersionFactory  {

        private final InfoPicocliVersionAccessors vaccForInfoPicocliVersionAccessors = new InfoPicocliVersionAccessors(providers, config);
        public InfoVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.info.picocli</b>
         */
        public InfoPicocliVersionAccessors getPicocli() {
            return vaccForInfoPicocliVersionAccessors;
        }

    }

    public static class InfoPicocliVersionAccessors extends VersionFactory  {

        public InfoPicocliVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>info.picocli.picocli</b> with value <b>4.6.3</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getPicocli() { return getVersion("info.picocli.picocli"); }

    }

    public static class IoVersionAccessors extends VersionFactory  {

        private final IoPapermcVersionAccessors vaccForIoPapermcVersionAccessors = new IoPapermcVersionAccessors(providers, config);
        public IoVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.io.papermc</b>
         */
        public IoPapermcVersionAccessors getPapermc() {
            return vaccForIoPapermcVersionAccessors;
        }

    }

    public static class IoPapermcVersionAccessors extends VersionFactory  {

        private final IoPapermcPaperVersionAccessors vaccForIoPapermcPaperVersionAccessors = new IoPapermcPaperVersionAccessors(providers, config);
        public IoPapermcVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.io.papermc.paper</b>
         */
        public IoPapermcPaperVersionAccessors getPaper() {
            return vaccForIoPapermcPaperVersionAccessors;
        }

    }

    public static class IoPapermcPaperVersionAccessors extends VersionFactory  {

        private final IoPapermcPaperPaperVersionAccessors vaccForIoPapermcPaperPaperVersionAccessors = new IoPapermcPaperPaperVersionAccessors(providers, config);
        public IoPapermcPaperVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.io.papermc.paper.paper</b>
         */
        public IoPapermcPaperPaperVersionAccessors getPaper() {
            return vaccForIoPapermcPaperPaperVersionAccessors;
        }

    }

    public static class IoPapermcPaperPaperVersionAccessors extends VersionFactory  {

        public IoPapermcPaperPaperVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>io.papermc.paper.paper.api</b> with value <b>1.20.4-R0.1-SNAPSHOT</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getApi() { return getVersion("io.papermc.paper.paper.api"); }

    }

    public static class NetVersionAccessors extends VersionFactory  {

        private final NetKyoriVersionAccessors vaccForNetKyoriVersionAccessors = new NetKyoriVersionAccessors(providers, config);
        public NetVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.net.kyori</b>
         */
        public NetKyoriVersionAccessors getKyori() {
            return vaccForNetKyoriVersionAccessors;
        }

    }

    public static class NetKyoriVersionAccessors extends VersionFactory  {

        private final NetKyoriAdventureVersionAccessors vaccForNetKyoriAdventureVersionAccessors = new NetKyoriAdventureVersionAccessors(providers, config);
        public NetKyoriVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.net.kyori.adventure</b>
         */
        public NetKyoriAdventureVersionAccessors getAdventure() {
            return vaccForNetKyoriAdventureVersionAccessors;
        }

    }

    public static class NetKyoriAdventureVersionAccessors extends VersionFactory  {

        private final NetKyoriAdventureTextVersionAccessors vaccForNetKyoriAdventureTextVersionAccessors = new NetKyoriAdventureTextVersionAccessors(providers, config);
        public NetKyoriAdventureVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.net.kyori.adventure.text</b>
         */
        public NetKyoriAdventureTextVersionAccessors getText() {
            return vaccForNetKyoriAdventureTextVersionAccessors;
        }

    }

    public static class NetKyoriAdventureTextVersionAccessors extends VersionFactory  {

        private final NetKyoriAdventureTextSerializerVersionAccessors vaccForNetKyoriAdventureTextSerializerVersionAccessors = new NetKyoriAdventureTextSerializerVersionAccessors(providers, config);
        public NetKyoriAdventureTextVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.net.kyori.adventure.text.serializer</b>
         */
        public NetKyoriAdventureTextSerializerVersionAccessors getSerializer() {
            return vaccForNetKyoriAdventureTextSerializerVersionAccessors;
        }

    }

    public static class NetKyoriAdventureTextSerializerVersionAccessors extends VersionFactory  {

        public NetKyoriAdventureTextSerializerVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>net.kyori.adventure.text.serializer.legacy</b> with value <b>4.11.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getLegacy() { return getVersion("net.kyori.adventure.text.serializer.legacy"); }

    }

    public static class BundleAccessors extends BundleFactory {

        public BundleAccessors(ObjectFactory objects, ProviderFactory providers, DefaultVersionCatalog config, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) { super(objects, providers, config, attributesFactory, capabilityNotationParser); }

    }

    public static class PluginAccessors extends PluginFactory {

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

    }

}
