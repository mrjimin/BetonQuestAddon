package kr.mrjimin.betonquestaddon.betonquest.conversation

data class DialogSettings(
    val layout: DialogLayout = DialogLayout.NPC_TITLE,
    val closeButton: CloseButtonSettings = CloseButtonSettings()
)

data class CloseButtonSettings(
    val enabled: Boolean = true,
    val text: String = "Close",
    val closeWithEscape: Boolean = true
)