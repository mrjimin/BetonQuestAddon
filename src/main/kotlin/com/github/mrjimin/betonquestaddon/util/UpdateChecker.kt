package com.github.mrjimin.betonquestaddon.util

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.io.IOException
import java.net.URI
import java.util.*
import java.util.function.Consumer

class UpdateChecker(
    private val plugin: JavaPlugin,
    private val resourceId: Int
) {

    fun getVersion(consumer: Consumer<String>) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, Runnable {
            try {
                val uri = URI("https://api.spigotmc.org/legacy/update.php?resource=$resourceId")
                uri.toURL().openStream().use { inputStream ->
                    Scanner(inputStream).use { scanner ->
                        if (scanner.hasNext()) {
                            consumer.accept(scanner.next())
                        }
                    }
                }
            } catch (e: IOException) {
                plugin.logger.info("Unable to check for updates: ${e.message}")
            }
        })
    }

    companion object {
        fun checkForUpdates(plugin: JavaPlugin, resourceId: Int) {
            UpdateChecker(plugin, resourceId).getVersion { version ->
                if (plugin.pluginMeta.version < version) {
                    plugin.logger.warning("================================")
                    plugin.logger.warning("A new update is available! Latest version: $version")
                    plugin.logger.warning("You can download the update from here:")
                    plugin.logger.warning("https://www.spigotmc.org/resources/betonquestaddon.120813/")
                    plugin.logger.warning("================================")
                }
            }
        }

    }
}