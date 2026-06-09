package kr.mrjimin.betonquestaddon.betonquest.conversation

import io.papermc.paper.dialog.Dialog
import io.papermc.paper.registry.data.dialog.ActionButton
import io.papermc.paper.registry.data.dialog.DialogBase
import io.papermc.paper.registry.data.dialog.action.DialogAction
import io.papermc.paper.registry.data.dialog.body.DialogBody
import io.papermc.paper.registry.data.dialog.type.DialogType
import kr.mrjimin.betonquestaddon.config.ConfigsManager
import kr.mrjimin.betonquestaddon.util.toMMComponent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickCallback
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.conversation.Conversation
import org.betonquest.betonquest.conversation.ConversationColors
import org.betonquest.betonquest.conversation.ConversationIO
import org.bukkit.configuration.ConfigurationSection

class DialogConvIO(
    private val conv: Conversation,
    private val profile: OnlineProfile,
    private val colors: ConversationColors,
    private val configsManager: ConfigsManager
) : ConversationIO {

    private val options = mutableListOf<Component>()
    private var npcName: Component? = null
    private var npcText: Component? = null

    private val EMPTY = Component.empty()

    override fun begin() {}

    override fun setNpcResponse(npcName: Component, response: Component) {
        this.npcName = npcName
        this.npcText = response
    }

    override fun addPlayerOption(option: Component, properties: ConfigurationSection) {
        options.add(option)
    }

    override fun display() {
        if (npcText == null && options.isEmpty()) {
            end {}
            return
        }

        profile.player.showDialog(
            Dialog.create { builder -> builder.empty()
                .base(buildDialogBase())
                .type(buildDialogType())
            }
        )
    }

    private fun settings(): DialogSettings =
        configsManager.dialog().settings

    private fun escapeAllowed(): Boolean =
        settings().closeButton.enabled && settings().closeButton.closeWithEscape

    private fun buildDialogBase(): DialogBase {
        val name = npcName ?: EMPTY
        val text = npcText ?: EMPTY

        val body = when (settings().layout) {
            DialogLayout.NPC_TITLE ->
                DialogBody.plainMessage(colors.text.append(text))

            DialogLayout.FULL_BODY ->
                DialogBody.plainMessage(
                    colors.text
                        .append(colors.npc.append(name))
                        .append(Component.text(": "))
                        .append(text)
                )
        }

        val title = if (settings().layout == DialogLayout.NPC_TITLE) colors.npc.append(name) else EMPTY

        return DialogBase.builder(title)
            .canCloseWithEscape(escapeAllowed())
            .body(listOf(body))
            .build()
    }

    private fun buildDialogType(): DialogType {
        if (options.isEmpty()) return DialogType.notice()

        val buttons = options.mapIndexed { index, text ->
            ActionButton.builder(text)
                .width(settings().defaultButtonWidth)
                .action(
                    DialogAction.customClick(
                        { _, _ -> conv.passPlayerAnswer(index + 1) },
                        clickOptions()
                    )
                )
                .build()
        }

        return DialogType.multiAction(buttons)
            .columns(1)
            .apply {
                if (settings().closeButton.enabled) exitAction(buildExitButton())
            }
            .build()
    }

    private fun buildExitButton(): ActionButton =
        ActionButton.builder(settings().closeButton.text.toMMComponent())
            .width(settings().closeButton.width)
            .action(
                DialogAction.customClick(
                    { _, _ ->
                        // profile.player.sendMessage("closed dialog io")
                        conv.endConversation()
                    },
                    clickOptions()
                )
            )
            .build()

    private fun clickOptions(): ClickCallback.Options =
        ClickCallback.Options.builder()
            .uses(1)
            .lifetime(ClickCallback.DEFAULT_LIFETIME)
            .build()

    override fun clear() {
        options.clear()
        npcName = null
        npcText = null
    }

    override fun end(callback: Runnable) = callback.run()
}