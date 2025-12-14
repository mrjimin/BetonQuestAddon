package com.github.mrjimin.betonquestaddon.compatibilityold.itemsadder.events.animation

import dev.lone.itemsadder.api.ItemsAdder
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.event.online.OnlineEvent
import org.betonquest.betonquest.api.instruction.variable.Variable

class IaPlayAnimation(
    private val name: Variable<String>
) : OnlineEvent {

    override fun execute(profile: OnlineProfile) {
        ItemsAdder.playTotemAnimation(profile.player, name.getValue(profile))
    }
}