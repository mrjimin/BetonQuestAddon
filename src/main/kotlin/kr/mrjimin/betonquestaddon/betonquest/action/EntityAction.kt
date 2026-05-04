//package kr.mrjimin.betonquestaddon.betonquest.action
//
//import kr.mrjimin.betonquestaddon.manager.EntityManager
//import org.betonquest.betonquest.api.instruction.Argument
//import org.betonquest.betonquest.api.instruction.type.ItemWrapper
//import org.betonquest.betonquest.api.profile.Profile
//import org.betonquest.betonquest.api.quest.action.PlayerAction
//import org.bukkit.Location
//import org.bukkit.Rotation
//import org.bukkit.block.BlockFace
//import org.bukkit.entity.Display
//import org.bukkit.entity.EntityType
//import org.bukkit.entity.ItemDisplay
//import org.bukkit.inventory.ItemStack
//import kotlin.enums.enumEntries
//import kotlin.math.roundToInt
//
//class EntityAction(
//    private val locationArg: Argument<Location>,
//    private val itemArg: Argument<ItemWrapper>,
//    private val rotationArg: Argument<Rotation>,
//    private val blockFaceArg: Argument<BlockFace>,
//    private val billboardArg: Argument<Display.Billboard>
//) : PlayerAction {
//
//    override fun execute(profile: Profile) {
//        val location = locationArg.getValue(profile)
//        val item = itemArg.getValue(profile).generate(profile)
//        val rotation = rotationArg.getValue(profile)
//        val blockFace = blockFaceArg.getValue(profile)
//        val billboard = billboardArg.getValue(profile)
//
//        spawnDisplay(location, item, rotation, blockFace, billboard)
//    }
//
//    private fun spawnDisplay(
//        location: Location,
//        itemStack: ItemStack,
//        rotation: Rotation,
//        blockFace: BlockFace,
//        billboard: Display.Billboard
//    ) {
//        val world = location.world ?: return
//
//        val display = world.spawnEntity(location, EntityType.ITEM_DISPLAY) as ItemDisplay
//
//        display.setItemStack(itemStack.clone().apply {
//            amount = 1
//        })
//
//        display.billboard = billboard
//        display.setRotation(rotation)
//
//        val offset = when (blockFace) {
//            BlockFace.UP -> location.clone().add(0.0, 0.2, 0.0)
//            BlockFace.DOWN -> location.clone().add(0.0, -0.2, 0.0)
//            BlockFace.NORTH -> location.clone().add(0.0, 0.0, -0.2)
//            BlockFace.SOUTH -> location.clone().add(0.0, 0.0, 0.2)
//            BlockFace.WEST -> location.clone().add(-0.2, 0.0, 0.0)
//            BlockFace.EAST -> location.clone().add(0.2, 0.0, 0.0)
//            else -> location
//        }
//
//        display.teleport(offset)
//
//        EntityManager.register(display, 60L)
//    }
//
//    fun rotationToYaw(rotation: Rotation): Float {
//        return (enumEntries<Rotation>().indexOf(rotation) * 360f) / 8f
//    }
//
//    fun yawToRotation(yaw: Float): Rotation {
//        return Rotation.entries[(yaw / 45f).roundToInt() and 0x7]
//    }
//
//    fun place(location: Location, itemStack: ItemStack, rotation: Rotation, blockFace: BlockFace) {
//        if (!location.isWorldLoaded) return
//        val display = location.world.spawnEntity(location, EntityType.ITEM_DISPLAY) as ItemDisplay
//
//        display.setItemStack(itemStack.clone().apply {
//            amount = 1
//        })
//
//        display.setRotation(rotationToYaw(rotation), 0f)
//    }
//}