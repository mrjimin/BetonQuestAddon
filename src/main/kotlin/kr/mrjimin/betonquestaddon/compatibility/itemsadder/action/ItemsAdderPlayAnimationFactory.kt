package kr.mrjimin.betonquestaddon.compatibility.itemsadder.action

import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.action.OnlineActionAdapter
import org.betonquest.betonquest.api.quest.action.PlayerAction
import org.betonquest.betonquest.api.quest.action.PlayerActionFactory

class ItemsAdderPlayAnimationFactory : PlayerActionFactory {

    override fun parsePlayer(instruction: Instruction): PlayerAction? {
        val animation = instruction.string().get()
        return OnlineActionAdapter(
            ItemsAdderPlayAnimation(animation),
        )
    }


}