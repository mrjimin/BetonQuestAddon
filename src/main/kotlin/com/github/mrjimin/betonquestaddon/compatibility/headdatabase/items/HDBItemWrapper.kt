package com.github.mrjimin.betonquestaddon.compatibility.headdatabase.items

import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.betonquest.betonquest.item.QuestItem
import org.betonquest.betonquest.item.QuestItemWrapper

class HDBItemWrapper(
    private val itemId: Variable<String>
) : QuestItemWrapper {

    override fun getItem(profile: Profile?): QuestItem {
        return HDBItem(itemId.getValue(profile))
    }
}