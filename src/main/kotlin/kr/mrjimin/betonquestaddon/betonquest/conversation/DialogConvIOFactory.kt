package kr.mrjimin.betonquestaddon.betonquest.conversation

import kr.mrjimin.betonquestaddon.config.ConfigsManager
import org.betonquest.betonquest.api.common.component.ComponentLineWrapper
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.conversation.Conversation
import org.betonquest.betonquest.conversation.ConversationColors
import org.betonquest.betonquest.conversation.ConversationIO
import org.betonquest.betonquest.conversation.ConversationIOFactory

class DialogConvIOFactory(
    private val colors: ConversationColors,
    private val componentLineWrapper: ComponentLineWrapper,
    private val configsManager: ConfigsManager
) : ConversationIOFactory {
    override fun parse(conversation: Conversation, onlineProfile: OnlineProfile): ConversationIO {
        return DialogConvIO(conversation, onlineProfile, colors, componentLineWrapper, configsManager)
    }
}