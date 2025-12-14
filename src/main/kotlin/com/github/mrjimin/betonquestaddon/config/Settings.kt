package com.github.mrjimin.betonquestaddon.config

import com.github.mrjimin.betonquestaddon.BetonQuestAddonPlugin

enum class Settings(
    val path: String,
    val default: Any? = null
) {
    // setting
    // AUTO_RELOAD("setting.auto-reload", false),

    // objectives
    OBJ_CHAT("objectives.chatObjective.argumentName","cancel"),

    PV_LINE_WEIGHT("compatibility.plasmovoice.source_line_weight"),
    PV_LINE_VOLUME("compatibility.plasmovoice.source_line_volume"),
    PV_ICON("compatibility.plasmovoice.icon");

    private val config
        get() = BetonQuestAddonPlugin.INSTANCE.config

    fun get(): Any? = config.get(path) ?: default

    override fun toString(): String = get().toString()
    fun toBoolean(): Boolean = get().toString().toBoolean()
    fun toInt(): Int = get().toString().toInt()
    fun toDouble(): Double = get().toString().toDouble()
}