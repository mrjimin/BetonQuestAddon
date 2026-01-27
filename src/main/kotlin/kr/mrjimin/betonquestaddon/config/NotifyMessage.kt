package kr.mrjimin.betonquestaddon.config

enum class NotifyMessage(private val key: String) {
    BLOCK_PLACE("block_place"),
    BLOCK_BREAK("block_break"),
    BLOCK_INTERACT("block_interact"),

    FURNITURE_PLACE("furniture_place"),
    FURNITURE_BREAK("furniture_break"),
    FURNITURE_INTERACT("furniture_interact"),

    // CustomCrops
    CUSTOM_CROPS_CROP_HARVEST("customcrops.crop_harvest"),
    CUSTOM_CROPS_CROP_PLANT("customcrops.crop_plant"),
    CUSTOM_CROPS_POT_PLACE("customcrops.pot_place"),
    CUSTOM_CROPS_POT_BREAK("customcrops.pot_break"),
    CUSTOM_CROPS_CAN_FILL("customcrops.can_fill"),
    CUSTOM_CROPS_CAN_POT("customcrops.can_pot"),
    CUSTOM_CROPS_CAN_SPRINKLER("customcrops.can_sprinkler"),
    CUSTOM_CROPS_SPRINKLER_PLACE("customcrops.sprinkler_place"),
    CUSTOM_CROPS_SPRINKLER_BREAK("customcrops.sprinkler_break"),
    CUSTOM_CROPS_USE_FERTILIZER("customcrops.use_fertilizer"),
    CUSTOM_CROPS_SCARECROW_PLACE("customcrops.scarecrow_place"),
    CUSTOM_CROPS_SCARECROW_BREAK("customcrops.scarecrow_break"),

    // CustomFishing
    CUSTOM_FISHING_CAUGHT_FISH("customfishing.caught_fish"),
    CUSTOM_FISHING_CAUGHT_GROUP("customfishing.caught_group"),
    CUSTOM_FISHING_ACTIVATE_TOTEM("customfishing.activate_totem"),

    ;

    fun toKey(): String = "betonquestaddon.$key"
}