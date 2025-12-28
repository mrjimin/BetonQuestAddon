package com.github.mrjimin.betonquestaddon.compatibility.itemsadder.event

import dev.lone.itemsadder.api.ItemsAdder
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.event.online.OnlineEvent

class PlayAnimation(
    private val name: Argument<String>
) : OnlineEvent {

    override fun execute(profile: OnlineProfile) {
        ItemsAdder.playTotemAnimation(profile.player, name.getValue(profile))
    }

    override fun isPrimaryThreadEnforced(): Boolean {
        return true
    }
}