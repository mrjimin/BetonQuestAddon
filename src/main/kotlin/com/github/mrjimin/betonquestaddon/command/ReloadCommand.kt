package com.github.mrjimin.betonquestaddon.command

import com.github.mrjimin.betonquestaddon.BetonQuestAddonPlugin
import com.github.mrjimin.betonquestaddon.util.toMiniMessage
import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.executors.CommandExecutor

class ReloadCommand(private val plugin: BetonQuestAddonPlugin) {

    fun build(): CommandAPICommand {
        return CommandAPICommand("reload")
            .withAliases("rl")
            .withPermission("betonquestaddon.command.reload")
            .executes(CommandExecutor { sender, _ ->
                // ConfigsManager(plugin).reload()
                sender.sendMessage("<#888888>BetonQuestAddon</#888888> <green>Reloaded successfully!".toMiniMessage())
            })
    }
}
