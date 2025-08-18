package com.github.mrjimin.betonquestaddon.compatibility.plasmovoice

import su.plo.config.Config
import su.plo.config.ConfigField
import su.plo.config.provider.ConfigurationProvider
import su.plo.config.provider.toml.TomlConfiguration
import su.plo.slib.api.server.McServerLib
import su.plo.voice.api.server.PlasmoVoiceServer
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.net.URI

@Config
class AddonConfig {

    @ConfigField
    var sourceLineWeight = 10

    @ConfigField(
        comment = """
            The default volume. Volume is configured on the client side
            and can be adjusted via the mod settings.
        """
    )
    var defaultSourceLineVolume = 0.5

    companion object {
        fun loadConfig(server: PlasmoVoiceServer): AddonConfig {
            val addonFolder = getAddonFolder(server.minecraftServer)

            server.languages.register(
                URI.create("https://github.com/plasmoapp/plasmo-voice-crowdin/archive/refs/heads/addons.zip").toURL(),
                "server/config.toml",
                { resourcePath: String -> getLanguageResource(resourcePath)
                    ?: throw Exception("Can't load language resource") },
                File(addonFolder, "pv-addon-bq/languages")
            )
            val configFile = File(addonFolder, "pv-addon-bq/config.toml")

            return toml.load<AddonConfig>(AddonConfig::class.java, configFile, false)
                .also { toml.save(AddonConfig::class.java, it, configFile) }
        }

        @Throws(IOException::class)
        private fun getLanguageResource(resourcePath: String): InputStream? =
            javaClass.classLoader.getResourceAsStream(String.format("pv-addon-bq/%s", resourcePath))

        private val toml = ConfigurationProvider.getProvider<ConfigurationProvider>(
            TomlConfiguration::class.java
        )

        private fun getAddonFolder(minecraftServer: McServerLib): File =
            File(minecraftServer.configsFolder, "BetonQuestAddon")
    }
}