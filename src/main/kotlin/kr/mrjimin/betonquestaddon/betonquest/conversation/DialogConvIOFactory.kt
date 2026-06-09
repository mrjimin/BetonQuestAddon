package kr.mrjimin.betonquestaddon.betonquest.conversation

import kr.mrjimin.betonquestaddon.config.ConfigsManager
import kr.mrjimin.betonquestaddon.config.DialogConfig
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.conversation.Conversation
import org.betonquest.betonquest.conversation.ConversationColors
import org.betonquest.betonquest.conversation.ConversationIO
import org.betonquest.betonquest.conversation.ConversationIOFactory

class DialogConvIOFactory(
    private val configsManager: ConfigsManager,
    private val colors: ConversationColors
) : ConversationIOFactory {
    override fun parse(conversation: Conversation, onlineProfile: OnlineProfile): ConversationIO {
        return DialogConvIO(conversation, onlineProfile, colors, configsManager)
    }
}