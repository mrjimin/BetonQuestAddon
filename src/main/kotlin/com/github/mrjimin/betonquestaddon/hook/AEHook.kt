package com.github.mrjimin.betonquestaddon.hook

import com.github.mrjimin.betonquestaddon.util.NotFoundPlugin
import com.github.mrjimin.betonquestaddon.util.checkPlugin
import net.advancedplugins.ae.api.AEAPI

object AEHook {

    init {
        if (!"AdvancedEnchantments".checkPlugin()) throw NotFoundPlugin("AdvancedEnchantments")
    }

    fun hasGroup(group: String): Boolean = group in getGroups()

    fun getGroups(): List<String> = AEAPI.getGroups() + "ANY"

}