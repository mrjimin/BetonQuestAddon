package com.github.mrjimin.betonquestaddon.config

import com.github.mrjimin.betonquestaddon.BetonQuestAddonPlugin
import com.github.mrjimin.betonquestaddon.api.BQAddonItems
import com.github.mrjimin.betonquestaddon.item.ItemBuilder
import com.github.mrjimin.betonquestaddon.item.ItemParser
import com.github.mrjimin.betonquestaddon.util.server.checkPlugin
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class ConfigsManager(private val plugin: BetonQuestAddonPlugin) {

    private val itemsFolder = File(plugin.dataFolder, "items").apply { if (!exists()) mkdirs() }

    fun reload() {
//        plugin.saveDefaultConfig()
//        plugin.reloadConfig()

//        if ("PlasmoVoice".checkPlugin() && PVAddonPlugin.isInitialized) {
//            PVConfigsManager(plugin).reload(PVAddonPlugin.voiceServer)
//        }

        BQAddonItems.loadItems(this)
    }

    fun parseItemConfig(): Map<File, MutableMap<String, ItemBuilder>> {
        return itemsFolder.walkTopDown()
            .filter { it.isFile && it.extension.equals("yml", ignoreCase = true) }
            .associateWith { file ->
                val config = YamlConfiguration.loadConfiguration(file)
                config.getKeys(false).mapNotNull { itemId ->
                    config.getConfigurationSection(itemId)?.let { section ->
                        itemId to ItemParser(section).buildItem(itemId)
                    }
                }.toMap().toMutableMap()
            }
    }

}
