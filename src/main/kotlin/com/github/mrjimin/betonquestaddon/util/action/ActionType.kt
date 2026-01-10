package com.github.mrjimin.betonquestaddon.util.action

enum class ActionType(val key: String, val targetType: TargetType) {
    BREAK_BLOCK("break_block", TargetType.BLOCK),
    PLACE_BLOCK("place_block", TargetType.BLOCK),
    INTERACT_BLOCK("interact_block", TargetType.BLOCK),

    BREAK_FURNITURE("break_furniture", TargetType.FURNITURE),
    PLACE_FURNITURE("place_furniture", TargetType.FURNITURE),
    INTERACT_FURNITURE("interact_furniture", TargetType.FURNITURE);

    fun toKey(): String = key
}