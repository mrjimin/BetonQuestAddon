package com.github.mrjimin.betonquestaddon.test

import net.momirealms.customcrops.api.event.CropBreakEvent
import org.bukkit.event.Listener

class TestListener : Listener {

    fun CropBreakEvent.on() {
        cropStageItemID()
    }
}