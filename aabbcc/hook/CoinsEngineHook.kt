package com.github.mrjimin.betonquestaddon.hook

import com.github.mrjimin.betonquestaddon.util.server.NotFoundPlugin
import com.github.mrjimin.betonquestaddon.util.server.checkPlugin
import org.betonquest.betonquest.api.quest.QuestException
import su.nightexpress.coinsengine.api.CoinsEngineAPI
import su.nightexpress.coinsengine.api.currency.Currency
import java.util.*

object CoinsEngineHook {

    init {
        if (!"CoinsEngine".checkPlugin()) throw NotFoundPlugin("CoinsEngine")
    }

    fun getCurrencyOrNull(id: String): Currency {
        return CoinsEngineAPI.getCurrency(id)
            ?: throw QuestException("Invalid or missing CoinsEngine currency ID: $id")
    }

    fun getBalance(uuid: UUID, id: String): Double {
        val currency = getCurrencyOrNull(id)
        return CoinsEngineAPI.getBalance(uuid, currency)
    }

    fun resetBalance(uuid: UUID, id: String) {
        val currency = getCurrencyOrNull(id)
        CoinsEngineAPI.setBalance(uuid, currency, 0.0)
    }

    fun setBalance(uuid: UUID, id: String, value: Double) {
        val currency = getCurrencyOrNull(id)
        CoinsEngineAPI.setBalance(uuid, currency, value)
    }

    fun addBalance(uuid: UUID, id: String, value: Double) {
        val currency = getCurrencyOrNull(id)
        CoinsEngineAPI.addBalance(uuid, currency, value)
    }

    fun removeBalance(uuid: UUID, id: String, value: Double) {
        val currency = getCurrencyOrNull(id)
        CoinsEngineAPI.removeBalance(uuid, currency, value)
    }

}
