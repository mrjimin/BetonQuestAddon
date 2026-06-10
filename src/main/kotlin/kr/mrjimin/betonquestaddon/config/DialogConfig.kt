package kr.mrjimin.betonquestaddon.config

import kr.mrjimin.betonquestaddon.betonquest.conversation.CloseButtonSettings
import kr.mrjimin.betonquestaddon.betonquest.conversation.DialogLayout
import kr.mrjimin.betonquestaddon.betonquest.conversation.DialogSettings
import org.bukkit.configuration.file.FileConfiguration

class DialogConfig(private val config: FileConfiguration) {

    val settings: DialogSettings
        get() {
            val section = config.getConfigurationSection("conversation.dialog")

            val layout = section?.getString("layout")?.uppercase()
                ?.let { runCatching { DialogLayout.valueOf(it) }.getOrDefault(DialogLayout.NPC_TITLE) }
                ?: DialogLayout.NPC_TITLE

            val buttonRenderPadding = section?.getInt("button-render-padding") ?: 13
            val defaultButtonWidth = section?.getInt("default-button-width") ?: 250

            val closeSection = section?.getConfigurationSection("close-button")
            val closeButton = CloseButtonSettings(
                closeSection?.getBoolean("enabled") ?: true,
                closeSection?.getString("text") ?: "Close",
                closeSection?.getInt("width") ?: defaultButtonWidth,
                closeSection?.getBoolean("close-with-escape") ?: true,
            )

            return DialogSettings(layout, closeButton, buttonRenderPadding, defaultButtonWidth)
        }
}