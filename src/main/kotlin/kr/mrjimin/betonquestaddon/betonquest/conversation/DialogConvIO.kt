package kr.mrjimin.betonquestaddon.betonquest.conversation

import io.papermc.paper.dialog.Dialog
import io.papermc.paper.registry.data.dialog.ActionButton
import io.papermc.paper.registry.data.dialog.DialogBase
import io.papermc.paper.registry.data.dialog.action.DialogAction
import io.papermc.paper.registry.data.dialog.type.DialogType
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickCallback
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.conversation.Conversation
import org.betonquest.betonquest.conversation.ConversationColors
import org.betonquest.betonquest.conversation.ConversationIO
import org.bukkit.Bukkit
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.event.EventHandler
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin

class DialogConvIO(
    private val plugin: Plugin,
    private val conv: Conversation,
    private val profile: OnlineProfile,
    private val colors: ConversationColors
) : ConversationIO, Listener {

    private val options: MutableMap<Int, Component> = mutableMapOf()
    private var optionsCount: Int = 0
    private var npcName: Component = Component.empty()
    private var npcText: Component = Component.empty()

    override fun begin() {
        Bukkit.getPluginManager().registerEvents(this, plugin)
    }

    override fun setNpcResponse(npcName: Component, response: Component) {
        this.npcName = npcName
        this.npcText = response
    }

    override fun addPlayerOption(option: Component, properties: ConfigurationSection) {
        optionsCount++
        options[optionsCount] = option
    }

    override fun display() {
        if (npcText == Component.empty() && options.isEmpty()) {
            end { }
            return
        }

        val title = colors.text
            .append(colors.npc.append(npcName))
            .append(Component.text(": "))
            .append(npcText)

        val actionButtons: List<ActionButton> = options.map { (index, text) ->
            ActionButton.builder(text)
                .action(
                    DialogAction.customClick({ _, _ ->
                        conv.passPlayerAnswer(index)
                        display()
                    },   ClickCallback.Options.builder()
                        .uses(1)
                        .lifetime(ClickCallback.DEFAULT_LIFETIME)
                        .build()
                    )
                )
                .build()
        }

        val dialog = Dialog.create { builder -> builder.empty()
            .base(DialogBase.builder(title).build())
            .type(
                DialogType.multiAction(actionButtons).build()
            )
        }
        profile.player.showDialog(dialog)
    }

    override fun clear() {
        optionsCount = 0
        options.clear()
        npcText = Component.empty()
    }

    override fun end(callback: Runnable) {
        HandlerList.unregisterAll(this)
        callback.run()
    }

    @EventHandler(ignoreCancelled = true)
    fun dialog() {}
}