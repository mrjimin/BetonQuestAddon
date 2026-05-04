//package kr.mrjimin.betonquestaddon.betonquest.action
//
//import org.betonquest.betonquest.api.instruction.Instruction
//import org.betonquest.betonquest.api.quest.action.PlayerAction
//import org.betonquest.betonquest.api.quest.action.PlayerActionFactory
//
//class EntityActionFactory : PlayerActionFactory {
//    override fun parsePlayer(instruction: Instruction): PlayerAction {
//        val location = instruction.location().get()
//        val itemStack = instruction.item().get()
//        return EntityAction(location, itemStack)
//    }
//}