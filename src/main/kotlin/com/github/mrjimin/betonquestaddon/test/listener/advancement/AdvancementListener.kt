//package com.github.mrjimin.betonquestaddon.test.listener.advancement
//
//import com.fren_gor.ultimateAdvancementAPI.AdvancementTab
//import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement
//import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement
//import com.fren_gor.ultimateAdvancementAPI.advancement.RootAdvancement
//import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay
//import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType
//import com.fren_gor.ultimateAdvancementAPI.events.PlayerLoadingCompletedEvent
//import com.github.mrjimin.betonquestaddon.BetonQuestAddonPlugin
//import com.github.mrjimin.betonquestaddon.util.toOnlinePlayer
//import org.betonquest.betonquest.api.bukkit.event.PlayerConversationStartEvent
//import org.betonquest.betonquest.api.bukkit.event.PlayerObjectiveChangeEvent
//import org.bukkit.Material
//import org.bukkit.entity.Player
//import org.bukkit.event.EventHandler
//import org.bukkit.event.Listener
//import org.bukkit.event.block.BlockBreakEvent
//import org.bukkit.inventory.ItemStack
//
//class AdvancementListener : Listener {
//
//    private val advancementTab = BetonQuestAddonPlugin.advancement.createAdvancementTab("your_tab_name")
//
//    init {
//        advancementTab.eventManager.register(advancementTab, PlayerLoadingCompletedEvent::class.java) { event ->
//            advancementTab.showTab(event.player)
//            advancementTab.grantRootAdvancement(event.player)
//        }
//
//        val root = RootAdvancement(
//            advancementTab,
//            "root",
//            AdvancementDisplay(
//                Material.OAK_SAPLING,
//                "Root Advancement",
//                AdvancementFrameType.CHALLENGE,
//                false,
//                false,
//                0f,
//                0f
//            ),
//            "textures/block/cobblestone.png"
//        )
//
//        val pickaxe = MineAdv(
//            "pickaxe",
//            AdvancementDisplay(
//                Material.STONE,
//                "Mine Stone",
//                AdvancementFrameType.TASK,
//                true,
//                true,
//                1.5f,
//                0f,
//                "Mine 10 blocks of stone."
//            ),
//            root,
//            10
//        )
//
//        advancementTab.registerAdvancements(root, pickaxe)
//    }
//
//
//}
//
//class MineAdv(
//    key: String,
//    display: AdvancementDisplay,
//    parent: Advancement,
//    maxProgression: Int
//) : BaseAdvancement(key, display, parent, maxProgression) {
//
//    init {
//        registerEvent(BlockBreakEvent::class.java) { e ->
//            val player = e.player
//            if (isVisible(player) && e.block.type == Material.STONE) {
//                incrementProgression(player)
//            }
//        }
//    }
//
//    override fun giveReward(player: Player) {
//        player.inventory.addItem(ItemStack(Material.STONE_BRICKS))
//    }
//}
//
////    private val advancementTab: AdvancementTab = BetonQuestAddonPlugin.advancement.createAdvancementTab("your_tab_name")
////    private val root: RootAdvancement
////
////    init {
////
////        val rootDisplay = AdvancementDisplay(
////            Material.GRASS_BLOCK,
////            "Example root",
////            AdvancementFrameType.TASK,
////            true,
////            true,
////            0f,
////            0f,
////            "description"
////        )
////
////        root = RootAdvancement(advancementTab, "root", rootDisplay, "textures/block/stone.png")
////        val root = RootAdvancement(
////            advancementTab,
////            "root",
////            AdvancementDisplay(
////                Material.OAK_SAPLING,
////                "Root Advancement",
////                AdvancementFrameType.CHALLENGE,
////                false,
////                false,
////                0f,
////                0f
////            ),
////            "textures/block/cobblestone.png"
////        )
////        val pickaxe = TestBQHook(
////            "pickaxe",
////            AdvancementDisplay(
////                Material.STONE,
////                "Test BQ",
////                AdvancementFrameType.TASK,
////                true,
////                true,
////                1.5f,
////                0f,
////                "PlayerObjectiveChangeEvent"
////            ),
////            root,
////        )
////
////        advancementTab.registerAdvancements(root)
////    }
////
////    @EventHandler
////    fun onJoin(e: PlayerLoadingCompletedEvent) {
////        println("aaa")
////        e.player.sendMessage("a")
////        advancementTab.showTab(e.player)
////    }
////}
////
////class TestBQHook(
////    key: String,
////    display: AdvancementDisplay,
////    parent: Advancement
////) : BaseAdvancement(key, display, parent) {
////
////    init {
////        registerEvent(PlayerObjectiveChangeEvent::class.java) { event ->
////            val player = event.profile.player.toOnlinePlayer()
////            if (isVisible(player)) {
////                incrementProgression(player)
////            }
////        }
////    }
////
////    override fun giveReward(player: Player) {
////        player.inventory.addItem(ItemStack(Material.STONE_BRICKS))
////    }
//
//// }