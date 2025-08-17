//package com.github.mrjimin.betonquestaddon.hook
//
//import com.github.mrjimin.betonquestaddon.BuildConstants
//import com.github.mrjimin.betonquestaddon.config.Settings
//import com.github.mrjimin.betonquestaddon.util.server.NotFoundPlugin
//import com.github.mrjimin.betonquestaddon.util.server.checkPlugin
//import kotlinx.coroutines.*
//import kotlinx.coroutines.future.await
//import org.bukkit.Location
//import org.bukkit.entity.Player
//import su.plo.voice.api.addon.AddonLoaderScope
//import su.plo.voice.api.addon.InjectPlasmoVoice
//import su.plo.voice.api.addon.annotation.Addon
//import su.plo.voice.api.server.PlasmoVoiceServer
//import su.plo.voice.api.server.audio.line.ServerSourceLine
//import su.plo.voice.api.server.audio.source.ServerProximitySource
//import su.plo.voice.discs.PlasmoAudioPlayerManager
//import su.plo.voice.discs.libraries.org.koin.core.Koin
//import su.plo.voice.discs.utils.PluginKoinComponent
//import su.plo.voice.discs.utils.PluginKoinComponentKt
//import java.util.concurrent.ConcurrentHashMap
//
//@Addon(
//    id = "pv-addon-betonquestaddon",
//    scope = AddonLoaderScope.SERVER,
//    version = BuildConstants.VERSION,
//    authors = ["MrJimin"]
//)
//object PVHook : PluginKoinComponent {
//
//    init {
//        if (!"PlasmoVoice".checkPlugin()) throw NotFoundPlugin("PlasmoVoice")
//    }
//
//    @InjectPlasmoVoice
//    lateinit var voiceServer: PlasmoVoiceServer
//
//    private val audioManager by lazy {
//        PlasmoAudioPlayerManager().apply { registerSources() }
//    }
//
//    val sourceLine: ServerSourceLine by lazy {
//        voiceServer.sourceLineManager.createBuilder(
//            this,
//            "betonquestaddon",
//            "pv.activation.betonquestaddon",
//            Settings.PV_ICON.toString(),
//            Settings.PV_LINE_WEIGHT.toInt()
//        ).apply {
//            setDefaultVolume(Settings.PV_LINE_VOLUME.toDouble())
//        }.build()
//    }
//
//    private val playerJobs: MutableMap<Player, Job> = ConcurrentHashMap()
//    private val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())
//
//    fun onLoad() = PlasmoVoiceServer.getAddonsLoader().load(this)
//    fun onDisable() {
//        stopAll()
//        PlasmoVoiceServer.getAddonsLoader().unload(this)
//    }
//
//    /**
//     * 플레이어에게 트랙 재생
//     */
//    fun play(player: Player, identifier: String, distance: Short = Settings.PV_LINE_DISTANCE.toShort()) {
//        scope.launch {
//            stop(player)?.join()
//            playerJobs[player] = startJob(player, identifier, distance)
//        }
//    }
//
//    /**
//     * 특정 플레이어 트랙 정지
//     */
//    fun stop(player: Player): Job? =
//        playerJobs.remove(player)?.also { it.cancel() }
//
//    /**
//     * 모든 플레이어 트랙 정지
//     */
//    fun stopAll() {
//        playerJobs.keys.toList().forEach { stop(it) }
//    }
//
//    /**
//     * 실제 트랙 재생 Job
//     */
//    private fun startJob(player: Player, identifier: String, distance: Short): Job =
//        scope.launch {
//            val voicePlayer = player.asVoicePlayer(voiceServer) ?: return@launch
//
//            // Bukkit API는 메인 스레드에서 실행
//            val track = try {
//                audioManager.getTrack(identifier).await()
//            } catch (e: Exception) {
//                withContext(Dispatchers.Main) {
//                    voicePlayer.instance.sendActionBar("Failed to load track: ${e.message}")
//                }
//                return@launch
//            }
//
//            // PlayerSource 생성도 메인 스레드에서
//            val source: ServerProximitySource<*> = withContext(Dispatchers.Main) {
//                sourceLine.createPlayerSource(voicePlayer, !Settings.PV_LINE_MONO)
//            }
//
//            // 트랙 재생은 비동기에서
//            val job = audioManager.startTrackJob(track, source, distance)
//            try {
//                job.join()
//            } finally {
//                withContext(NonCancellable + Dispatchers.Main) {
//                    job.cancelAndJoin()
//                    source.remove()
//                    playerJobs.remove(player)
//                }
//            }
//        }
//
//
//    override fun getKoin(): Koin = PluginKoinComponentKt.getKOIN_INSTANCE()
//}
