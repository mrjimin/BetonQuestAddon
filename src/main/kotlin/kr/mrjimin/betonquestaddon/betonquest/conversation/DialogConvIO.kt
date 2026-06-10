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
import org.betonquest.betonquest.api.common.component.ComponentLineWrapper
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.conversation.Conversation
import org.betonquest.betonquest.conversation.ConversationColors
import org.betonquest.betonquest.conversation.ConversationIO
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.event.Listener

class DialogConvIO(
    private val conv: Conversation,
    private val profile: OnlineProfile,
    private val colors: ConversationColors,
    private val componentLineWrapper: ComponentLineWrapper,
    private val configsManager: ConfigsManager
) : ConversationIO, Listener {

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

        val width = computeDialogWidth()

        val buttons = options.mapIndexed { index, option ->
            buildPlayerOptionButton(option, index, width)
        }

        return DialogType.multiAction(buttons)
            .columns(1)
            .apply {
                if (settings().closeButton.enabled) {
                    exitAction((buildExitButton(width)))
                }
            }
            .build()
    }

    private fun buildPlayerOptionButton(option: Component, index: Int, width: Int): ActionButton {
        return ActionButton.builder(option)
            .width(width)
            .action(
                DialogAction.customClick(
                    { _, _ -> conv.passPlayerAnswer(index + 1) },
                    clickOptions()
                )
            )
            .build()
    }

    private fun computeDialogWidth(): Int {
        val settings = settings()
        val optionWidth = options.maxOfOrNull { componentLineWrapper.width(it) + settings().buttonRenderPadding } ?: 0
        val defaultWidth = settings.defaultButtonWidth.takeIf { it > 0 } ?: 0
        return maxOf(optionWidth, defaultWidth, 100)
    }

    private fun buildExitButton(width: Int): ActionButton {
        val closeSettings = settings().closeButton
        val closeText = closeSettings.text.toMMComponent()

        val finalWidth = when {
            closeSettings.width > 0 -> closeSettings.width
            closeSettings.width == -1 -> width
            else -> componentLineWrapper.width(closeText) + settings().buttonRenderPadding
        }

        return ActionButton.builder(closeText)
            .width(finalWidth)
            .action(
                DialogAction.customClick(
                    { _, _ -> conv.endConversation() },
                    clickOptions()
                )
            )
            .build()
    }

//    private fun buildExitButton(): ActionButton {
//        val closeSettings = settings().closeButton
//        val closeText = closeSettings.text.toMMComponent()
//
//        val finalCloseWidth = if (closeSettings.width == -1) {
//            if (settings().defaultButtonWidth == -1) {
//                componentLineWrapper.width(closeText)
//            } else settings().defaultButtonWidth
//        } else closeSettings.width
//
//        return ActionButton.builder(closeText)
//            .width(finalCloseWidth)
//            .action(
//                DialogAction.customClick(
//                    { _, _ -> conv.endConversation() },
//                    clickOptions()
//                )
//            )
//            .build()
//        }

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