//package com.github.mrjimin.betonquestaddon.betonquest.conversation
//
//import net.kyori.adventure.text.Component
//import org.betonquest.betonquest.BetonQuest
//import org.betonquest.betonquest.api.profile.OnlineProfile
//import org.betonquest.betonquest.conversation.Conversation
//import org.betonquest.betonquest.conversation.ConversationColors
//import org.betonquest.betonquest.conversation.ConversationIO
//import org.bukkit.configuration.ConfigurationSection
//
//class TitleConvIO(
//    private val conv: Conversation,
//    private val onlineProfile: OnlineProfile,
//    private val colors: ConversationColors,
//) : ConversationIO {
//
//    var optionsCount: Int = 0
//    var options: MutableMap<Int, Component> = HashMap()
//    var npcText: Component = Component.empty()
//    var npcName: Component = Component.empty()
//
//    override fun setNpcResponse(npcName: Component, response: Component) {
//        this.npcName = npcName
//        this.npcText = response
//    }
//
//    override fun addPlayerOption(option: Component, properties: ConfigurationSection) {
//        optionsCount++
//        options[optionsCount] = option
//    }
//
//    override fun display() {
//        if (npcText == Component.empty() && options.isEmpty()) {
//            end {}
//            return
//        }
//
//         conv.showTitle(npcName, npcText, 10, 60, 10)
//    }
//
//    override fun clear() {
//        optionsCount = 0
//        options.clear()
//        npcText = Component.empty()
//    }
//
//    override fun end(callback: Runnable) {
//        callback.run()
//        conv.endConversation()
//    }
//}