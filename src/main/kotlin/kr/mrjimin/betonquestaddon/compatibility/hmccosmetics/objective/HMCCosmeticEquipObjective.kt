package kr.mrjimin.betonquestaddon.compatibility.hmccosmetics.objective

import com.hibiscusmc.hmccosmetics.api.events.PlayerCosmeticEquipEvent
import kr.mrjimin.betonquestaddon.util.DefaultOptions
import org.betonquest.betonquest.api.CountingObjective
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class HMCCosmeticEquipObjective(
    service: ObjectiveService,
    options: DefaultOptions,
    private val id: Argument<String>
) : CountingObjective(service, options.amount, "") {

    fun onCosmeticEquip(event: PlayerCosmeticEquipEvent, profile: OnlineProfile) {
        val cosmeticsId = event.cosmetic.id ?: return

        if (id.getValue(profile).equals(cosmeticsId)) {
            getCountingData(profile)?.progress()
            completeIfDoneOrNotify(profile)
        }
    }

}