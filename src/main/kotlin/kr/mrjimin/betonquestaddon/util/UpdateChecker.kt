package kr.mrjimin.betonquestaddon.util

import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin
import java.net.URI

class UpdateChecker(private val plugin: JavaPlugin) : Listener {

    init {
        plugin.server.pluginManager.registerEvents(this, plugin)
    }

    private val id = "XvDcVrRl"
    private val url = "https://api.modrinth.com/v2/project/$id/version"
    private val regex = """version_number":"([^"]+)".*?"version_type":"([^"]+)"""".toRegex()

    fun checkForUpdates(sender: CommandSender = Bukkit.getConsoleSender()) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, Runnable {
            runCatching { URI(url).toURL().readText() }.onSuccess { response ->
                regex.find(response ?: "")?.destructured?.let { (latest, type) ->
                    if (isNewer(plugin.pluginMeta.version, latest)) {
                        printUpdateMessage(sender, latest, type)
                    }
                }
            }
        })
    }

    private fun printUpdateMessage(sender: CommandSender, latest: String, type: String) {
        val (color, label) = when (type.lowercase()) {
            "release" -> "#50fa7b" to "RELEASE"
            "beta" -> "#e3a814" to "BETA"
            "alpha" -> "#ff5555" to "ALPHA"
            else -> "#bd93f9" to type.uppercase()
        }

        listOf(
            "<color:#707070>========================================</color>",
            "BetonQuestAddon <color:#ff79c6>Update Available!</color>",
            "Version: <color:#00d2ff>v$latest</color> Type: <color:$color>$label</color>",
            "Link: <color:#f1fa8c><click:open_url:'https://modrinth.com/project/$id'>https://modrinth.com/project/$id</click></color>",
            "<color:#707070>========================================</color>"
        ).also { lines ->
            if (sender is Player) {
                lines.forEach { sender.sendMessage(it.toMMComponent()) }
            } else {
                lines.forEach { Logger.info(it) }
            }
        }
    }

    private fun isNewer(current: String, latest: String): Boolean {
        val currParts = current.split('.').map { it.toIntOrNull() ?: 0 }
        val lateParts = latest.split('.').map { it.toIntOrNull() ?: 0 }

        for (i in 0 until maxOf(currParts.size, lateParts.size)) {
            val curr = currParts.getOrElse(i) { 0 }
            val late = lateParts.getOrElse(i) { 0 }
            if (late > curr) return true
            if (late < curr) return false
        }
        return false
    }

    @EventHandler(ignoreCancelled = true)
    fun PlayerJoinEvent.onJoin() {
        if (player.isOp) checkForUpdates(player)
    }
}