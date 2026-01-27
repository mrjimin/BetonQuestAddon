package kr.mrjimin.betonquestaddon.compatibility.itemsadder.action

import dev.lone.itemsadder.api.ItemsAdder
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.action.online.OnlineAction

class ItemsAdderPlayAnimation(
    private val name: Argument<String>
) : OnlineAction {

    override fun execute(profile: OnlineProfile) {
        ItemsAdder.playTotemAnimation(profile.player, name.getValue(profile))
    }

    override fun isPrimaryThreadEnforced(): Boolean = true
}