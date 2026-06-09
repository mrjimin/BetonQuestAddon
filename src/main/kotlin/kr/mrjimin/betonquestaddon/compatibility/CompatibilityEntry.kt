package kr.mrjimin.betonquestaddon.compatibility

data class CompatibilityEntry(
    val name: String,
    val version: String,
    val instance: ICompatibility,
    val enabled: Boolean
)