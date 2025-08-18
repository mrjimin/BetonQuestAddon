package com.github.mrjimin.betonquestaddon.hook

import com.github.mrjimin.betonquestaddon.compatibility.plasmovoice.addon.PVAddonPlugin
import kotlinx.coroutines.*
import kotlinx.coroutines.future.await
import org.bukkit.Location
import org.bukkit.entity.Player
import su.plo.slib.api.server.position.ServerPos3d
import su.plo.voice.api.addon.InjectPlasmoVoice
import su.plo.voice.api.server.PlasmoVoiceServer
import su.plo.voice.api.server.audio.source.ServerProximitySource
import su.plo.voice.api.server.player.VoiceServerPlayer
import su.plo.voice.discs.PlasmoAudioPlayerManager
import su.plo.voice.discs.libraries.org.koin.core.Koin
import su.plo.voice.discs.utils.PluginKoinComponent
import su.plo.voice.discs.utils.PluginKoinComponentKt

object PVHook : PluginKoinComponent {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val trackMap = mutableMapOf<String, String>() // id -> url
    private val playerTrackJobs = mutableMapOf<String, Job>() // "uuid-id" or "location-id" -> Job

    @InjectPlasmoVoice
    lateinit var voiceServer: PlasmoVoiceServer

    private val audioManager by lazy { PlasmoAudioPlayerManager().apply { registerSources() } }

    fun onLoad() = PlasmoVoiceServer.getAddonsLoader().load(this)

    fun onDisable() {
        playerTrackJobs.values.forEach { it.cancel() }
        playerTrackJobs.clear()
        trackMap.clear()
        PlasmoVoiceServer.getAddonsLoader().unload(this)
    }

    private fun getJobKey(player: Player?, id: String) =
        if (player != null) "${player.uniqueId}-$id" else "location-$id"

    fun playTrack(player: Player, id: String, url: String, distance: Short = 16, loop: Boolean = false) {
        trackMap[id] = url
        val voicePlayer = player.asVoicePlayer(voiceServer)
        val jobKey = getJobKey(player, id)
        playerTrackJobs[jobKey]?.cancel()
        val job = scope.launch {
            try {
                do {
                    val track = audioManager.getTrack(url).await()
                    val source = PVAddonPlugin.sourceLine.createPlayerSource(voicePlayer, false) as ServerProximitySource<*>
                    audioManager.startTrackJob(track, source, distance)
                } while (loop)
            } catch (e: Exception) {
                player.sendMessage("§cFailed to play track: ${e.message}")
            }
        }
        playerTrackJobs[jobKey] = job
    }

    fun playTrackAtLocation(location: Location, id: String, url: String, distance: Short = 16, loop: Boolean = false) {
        trackMap[id] = url
        scope.launch {
            try {
                val mcWorld = voiceServer.minecraftServer.getWorld(location.world.name) ?: return@launch
                val pos = ServerPos3d(mcWorld, location.x, location.y, location.z)
                do {
                    val track = audioManager.getTrack(url).await()
                    val source = PVAddonPlugin.sourceLine.createStaticSource(pos, false)
                    audioManager.startTrackJob(track, source, distance)
                } while (loop)
            } catch (e: Exception) {
                println("Failed to play track at location: ${e.message}")
            }
        }
    }


    fun stopTrack(player: Player?, id: String) {
        val jobKey = getJobKey(player, id)
        playerTrackJobs.remove(jobKey)?.cancel()
        trackMap.remove(id)
    }

    fun stopTrackAll(id: String) {
        playerTrackJobs.keys.filter { it.endsWith("-$id") || it.startsWith("location-$id") }
            .forEach { playerTrackJobs.remove(it)?.cancel() }
        trackMap.remove(id)
    }

    fun getTrackUrl(id: String): String? = trackMap[id]
    fun getAllTracks(): Map<String, String> = trackMap.toMap()

    fun Player.asVoicePlayer(voiceServer: PlasmoVoiceServer): VoiceServerPlayer =
        voiceServer.playerManager.getPlayerByInstance(this)

    override fun getKoin(): Koin = PluginKoinComponentKt.getKOIN_INSTANCE()

}
