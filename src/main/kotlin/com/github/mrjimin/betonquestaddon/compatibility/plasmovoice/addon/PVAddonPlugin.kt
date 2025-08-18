package com.github.mrjimin.betonquestaddon.compatibility.plasmovoice.addon

import com.github.mrjimin.betonquestaddon.BuildConstants
import com.github.mrjimin.betonquestaddon.compatibility.plasmovoice.AddonConfig
import org.bukkit.plugin.java.JavaPlugin
import su.plo.slib.api.logging.McLoggerFactory
import su.plo.voice.api.addon.AddonLoaderScope
import su.plo.voice.api.addon.InjectPlasmoVoice
import su.plo.voice.api.addon.annotation.Addon
import su.plo.voice.api.addon.annotation.Dependency
import su.plo.voice.api.logging.DebugLogger
import su.plo.voice.api.server.PlasmoVoiceServer
import su.plo.voice.api.server.audio.line.ServerSourceLine

@Addon(
    id = "pv-addon-betonquestaddon",
    scope = AddonLoaderScope.SERVER,
    version = BuildConstants.VERSION,
    authors = ["MrJimin"],
    dependencies = [Dependency(id = "pv-addon-lavaplayer-lib")]
)
class PVAddonPlugin(val plugin: JavaPlugin) {

    private val addonName = "betonquestaddon"

    @InjectPlasmoVoice
    lateinit var voiceServer: PlasmoVoiceServer

    lateinit var sourceLine: ServerSourceLine
        private set

    private lateinit var addonConfig: AddonConfig
    private lateinit var debugLogger: DebugLogger

    fun load() {
        instance = this
        initialized = true
        PlasmoVoiceServer.getAddonsLoader().load(this)
    }

    fun onEnableAddon() {
        loadConfig()
        plugin.logger.info("PVAddonPlugin enabled (PlasmoVoice ready).")
    }

    fun unload() {
        PlasmoVoiceServer.getAddonsLoader().unload(this)
    }

    fun loadConfig() {
        addonConfig = AddonConfig.loadConfig(voiceServer)

        debugLogger = DebugLogger(McLoggerFactory.createLogger(plugin.slF4JLogger.name))
        debugLogger.enabled(voiceServer.debugLogger.enabled())

        voiceServer.sourceLineManager.unregister(addonName)

        sourceLine = voiceServer.sourceLineManager.createBuilder(
            this,
            addonName,
            "pv.activation.$addonName",
            "plasmovoice:textures/icons/speaker_disc.png",
            addonConfig.sourceLineWeight
        ).apply {
            setDefaultVolume(addonConfig.defaultSourceLineVolume)
        }.build()
    }

    companion object {
        lateinit var instance: PVAddonPlugin
            private set

        val sourceLine: ServerSourceLine
            get() = instance.sourceLine

        val voiceServer: PlasmoVoiceServer
            get() = instance.voiceServer

        private var initialized = false

        val isInitialized: Boolean
            get() = initialized
    }
}
