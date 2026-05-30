//package kr.mrjimin.betonquestaddon.compatibility.customfishing.objective
//
//import kr.mrjimin.betonquestaddon.betonquest.objective.AddonObjective
//import kr.mrjimin.betonquestaddon.config.NotifyMessage
//import kr.mrjimin.betonquestaddon.util.ObjectiveOptions
//import net.momirealms.customfishing.api.event.FishingResultEvent
//import net.momirealms.customfishing.api.event.MarketSellEvent
//import org.betonquest.betonquest.api.instruction.Argument
//import org.betonquest.betonquest.api.profile.OnlineProfile
//import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
//
//class MarketSellObjective(
//    service: ObjectiveService,
//    amount: Argument<Number>,
//    options: ObjectiveOptions,
//    notifyMessage: NotifyMessage
//) : AddonObjective<>(service, amount, options, notifyMessage) {
//
//    fun onSell(event: MarketSellEvent, profile: OnlineProfile) {
//
//        handle(profile, event.loot.id(), event)
//        event.player.sendMessage(event.loot.id())
//    }
//}