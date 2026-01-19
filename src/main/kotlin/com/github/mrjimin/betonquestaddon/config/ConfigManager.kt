package com.github.mrjimin.betonquestaddon.config

import com.github.mrjimin.betonquestaddon.util.Logger
import dev.dejvokep.boostedyaml.YamlDocument
import dev.dejvokep.boostedyaml.dvs.versioning.BasicVersioning
import dev.dejvokep.boostedyaml.libs.org.snakeyaml.engine.v2.common.ScalarStyle
import dev.dejvokep.boostedyaml.libs.org.snakeyaml.engine.v2.nodes.Tag
import dev.dejvokep.boostedyaml.settings.dumper.DumperSettings
import dev.dejvokep.boostedyaml.settings.general.GeneralSettings
import dev.dejvokep.boostedyaml.settings.loader.LoaderSettings
import dev.dejvokep.boostedyaml.settings.updater.UpdaterSettings
import dev.dejvokep.boostedyaml.utils.format.NodeRole
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.io.IOException

class ConfigManager(private val plugin: JavaPlugin) {

    private lateinit var MAIN_CONFIG: YamlDocument

    fun load() {
        try {
            MAIN_CONFIG = YamlDocument.create(
                File(plugin.dataFolder, "config.yml"),
                plugin.getResource("config.yml")!!,
                GeneralSettings.builder()
                    .setRouteSeparator('.')
                    .setUseDefaults(false)
                    .build(),
                LoaderSettings.builder().setAutoUpdate(true).build(),
                DumperSettings.builder()
                    .setScalarFormatter { tag, _, role, _ ->
                        when {
                            role == NodeRole.KEY -> ScalarStyle.PLAIN
                            tag == Tag.STR -> ScalarStyle.DOUBLE_QUOTED
                            else -> ScalarStyle.PLAIN
                        }
                    }.build(),
                UpdaterSettings.builder()
                    .setVersioning(BasicVersioning("config-version"))
                    .build()
            )
        } catch (e: IOException) {
            e.printStackTrace()
        }
        MAIN_CONFIG.save(File(plugin.dataFolder, "config.yml"))
        Logger.info("Loading config.yml")
    }

}