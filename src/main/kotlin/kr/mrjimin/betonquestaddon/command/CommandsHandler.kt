package kr.mrjimin.betonquestaddon.command

import kr.mrjimin.betonquestaddon.BetonQuestAddonPlugin
import kr.mrjimin.betonquestaddon.util.getPluginVersion
import kr.mrjimin.betonquestaddon.util.toMMComponent
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class CommandsHandler(
    private val plugin: BetonQuestAddonPlugin
) : Command("betonquestaddon") {

    init {
        permission = "betonquestaddon.command"
        aliases = listOf("bqa")
    }

    override fun execute(sender: CommandSender, commandLabel: String, args: Array<out String>): Boolean {
        if (!sender.hasPermission("betonquestaddon.command")) return true

        when (args.getOrNull(0)?.lowercase()) {
            "reload" -> {
                plugin.configsManager.reload()
                sender.sendMessage("[BetonQuestAddon] <green>Configs reloaded successfully!".toMMComponent())
            }
            "info" -> {
                sender.sendMessage("[BetonQuestAddon] <gold>Info".toMMComponent())
                sender.sendMessage("Version <color:#00d2ff>v${plugin.pluginMeta.version}".toMMComponent())
                sender.sendMessage("BetonQuest <dark_gray>v${getPluginVersion("BetonQuest")}".toMMComponent())
                sender.sendMessage("Server <color:#e3a814>${plugin.server.name}</color> <gray>(MC ${plugin.server.minecraftVersion})</gray>".toMMComponent())

                val compatList = plugin.compatManager.getHookedPlugins()
                if (!compatList.isEmpty()) {
                    compatList
                        .sortedBy { it.name }
                        .forEach {
                            sender.sendMessage(" - <green>${it.name} <dark_gray>v${it.version}".toMMComponent())
                        }
                }
            }
        }

        return true
    }

    override fun tabComplete(sender: CommandSender, alias: String, args: Array<out String>): List<String> {
        if (!sender.hasPermission("betonquestaddon.command")) return emptyList()

        return when (args.size) {
            1 -> listOf("reload", "info").filter { it.startsWith(args[0], ignoreCase = true) }
            else -> emptyList()
        }
    }

}