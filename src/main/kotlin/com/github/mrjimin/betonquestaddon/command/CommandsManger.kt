package com.github.mrjimin.betonquestaddon.command

import com.github.mrjimin.betonquestaddon.BetonQuestAddonPlugin
import com.github.mrjimin.betonquestaddon.compatibility.BQAddonIntegratorHandler
import com.github.mrjimin.betonquestaddon.util.server.MinecraftVersion
import com.github.mrjimin.betonquestaddon.util.server.checkPlugin
import com.github.mrjimin.betonquestaddon.util.toMiniMessage
import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.executors.CommandExecutor
import org.bukkit.command.ConsoleCommandSender

class CommandsManger(private val plugin: BetonQuestAddonPlugin) {

    fun loadsCommands() {
        CommandAPICommand("betonquestaddon")
            .withAliases("bqa", "bqaddon")
            .withPermission("betonquestaddon.command")
            .withSubcommands(
                ReloadCommand(plugin).build(),
                GiveCommand().build(),
                if (MinecraftVersion.V1_21_8.isExact()) GUICommand().build() else null
            )
            .executes(CommandExecutor { sender, _ ->
                val hookedPlugins = BQAddonIntegratorHandler.getHookedPlugins().sorted().joinToString(", ")
                val docs = "https://jiminstudio.github.io/betonquestaddon/"

                listOf(
                    "<green>${plugin.pluginMeta.name} Info</green>",
                    "<gray>┃ Version:</gray> <white>${plugin.pluginMeta.version}",
                    "<gray>┃ Author:</gray> <gold>${plugin.pluginMeta.authors.joinToString()}",
                    if (sender is ConsoleCommandSender)
                        "<gray>┃ Website:</gray> <blue>${plugin.pluginMeta.website}</blue><gray>,</gray> <blue>$docs</blue>"
                    else
                        "<gray>┃ Website:</gray> <click:open_url:'${plugin.pluginMeta.website}'><u><blue>Spigotmc</blue></u></click><gray>,</gray> <click:open_url:'$docs'><u><blue>Documentation</blue></u></click>",
                    "<gray>┃ Hooked Plugins:</gray> <color:#9940ff>$hookedPlugins</color>"
                ).map(String::toMiniMessage).forEach(sender::sendMessage)
            })
            .register()
    }
}
