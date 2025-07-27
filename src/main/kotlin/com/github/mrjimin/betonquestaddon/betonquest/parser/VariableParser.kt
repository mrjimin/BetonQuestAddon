package com.github.mrjimin.betonquestaddon.betonquest.parser

import org.betonquest.betonquest.api.config.quest.QuestPackage
import org.betonquest.betonquest.api.quest.QuestException
import org.betonquest.betonquest.id.ObjectiveID
import org.betonquest.betonquest.instruction.argument.PackageArgument
import java.util.AbstractMap

object VariableParser : PackageArgument<Map.Entry<ObjectiveID, String>> {
    override fun apply(questPackage: QuestPackage, string: String): Map.Entry<ObjectiveID, String> {
        val split = string.split("#")
        if (split.size != 2) {
            throw QuestException("Invalid variable '$string' does not contain ID and Key!")
        }
        val objectiveID = ObjectiveID(questPackage, split[0])
        return AbstractMap.SimpleEntry(objectiveID, split[1])
    }
}