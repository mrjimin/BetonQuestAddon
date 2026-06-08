package kr.mrjimin.betonquestaddon.betonquest.conversation

import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.conversation.Conversation
import org.betonquest.betonquest.conversation.ConversationColors
import org.betonquest.betonquest.conversation.ConversationIO
import org.betonquest.betonquest.conversation.ConversationIOFactory
import org.bukkit.plugin.Plugin

class DialogConvIOFactory(
    private val plugin: Plugin,
    private val colors: ConversationColors
) : ConversationIOFactory {
    override fun parse(conversation: Conversation, onlineProfile: OnlineProfile): ConversationIO {
        return DialogConvIO(plugin, conversation, onlineProfile, colors)
    }
}