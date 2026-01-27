package kr.mrjimin.betonquestaddon.compatibility.craftengine

import net.momirealms.craftengine.core.util.Key

fun String.asCraftKey(): Key = requireNotNull(Key.of(this))