package kr.mrjimin.betonquestaddon.util.action

enum class Action {
    PLACE, BREAK, INTERACT;

    fun toKey(): String {
        return this.name.lowercase()
    }

}