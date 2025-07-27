package com.github.mrjimin.betonquestaddon.test.listener.ae

import com.github.mrjimin.betonquestaddon.hook.AEHook
import net.advancedplugins.ae.api.AEAPI
import net.advancedplugins.ae.api.AlchemistTradeEvent
import net.advancedplugins.ae.api.BookOpenEvent
import net.advancedplugins.ae.api.EnchantApplyEvent
import net.advancedplugins.ae.api.EnchantDestroyItemEvent
import net.advancedplugins.ae.api.ItemApplyEvent
import net.advancedplugins.ae.api.SlotIncreaseEvent
import net.advancedplugins.ae.api.SoulApplyEvent
import net.advancedplugins.ae.api.SoulGemGiveEvent
import net.advancedplugins.ae.api.SoulUseEvent
import net.advancedplugins.ae.api.TinkererTradeEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class AEListener : Listener {

    @EventHandler
    fun AlchemistTradeEvent.on() {
        player.sendMessage("Alchemist Trade Event")
        println("Alchemist Trade Event")
    }

    @EventHandler
    fun BookOpenEvent.on() {

        player.sendMessage("Book Open Event")
        player.sendMessage(group)
        println("Book Open Event")
        println(group)
        println(AEAPI.getGroups())
        println(AEHook.hasGroup(group))
    }

    @EventHandler
    fun EnchantApplyEvent.on() {
        player.sendMessage("Enchant Apply Event")
        println("Enchant Apply Event")
        println(enchantment)
    }

    @EventHandler
    fun EnchantDestroyItemEvent.on() {
        println("Enchant DestroyItem Event")
    }

    @EventHandler
    fun ItemApplyEvent.on() {
        player.sendMessage("Slot Increas Event")
        println("Slot Increas Event")
    }

    @EventHandler
    fun SlotIncreaseEvent.on() {
        player.sendMessage("Slot Increas Event")
        println("Slot Increas Event")
    }

    @EventHandler
    fun SoulApplyEvent.on() {
        player.sendMessage("Soul Apply Event")
        println("Soul Apply Event")
    }

    @EventHandler
    fun SoulGemGiveEvent.on() {
        player.sendMessage("Soul Gem Gem Gem Event")
        println("Soul Gem Give Event")
    }

    @EventHandler
    fun SoulUseEvent.on() {
        println("SoulUse Event")
    }

    @EventHandler
    fun TinkererTradeEvent.on() {
        player.sendMessage("Tinkerer Trade Event")
        println("Tinkerer Trade Event")
    }

}