package kr.mrjimin.betonquestaddon.betonquest.conversation

data class DialogSettings(
    val layout: DialogLayout = DialogLayout.NPC_TITLE,
    val closeButton: CloseButtonSettings = CloseButtonSettings(),
    val buttonRenderPadding: Int = 13,
    val defaultButtonWidth: Int = 250
)

data class CloseButtonSettings(
    val enabled: Boolean = true,
    val text: String = "Close",
    val width: Int = 250,
    val closeWithEscape: Boolean = true
)