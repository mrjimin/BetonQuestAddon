package kr.mrjimin.betonquestaddon.betonquest.objective

import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.ObjectiveOptions
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.Location
import org.bukkit.event.Cancellable

abstract class AddonObjective<T>(
    service: ObjectiveService,
    amount: Argument<Number>,
    private val options: ObjectiveOptions,
    notifyMessage: NotifyMessage
) : AbstractObjective(service, amount, notifyMessage) {

    protected open fun getId(target: T): String? = (target as? String)
    protected open fun getTargetId(target: T): String? = null
    protected open fun getLocation(target: T): Location? = null
    protected open fun matchesTarget(profile: OnlineProfile, target: T): Boolean = true

    protected fun handle(profile: OnlineProfile, target: T, event: Cancellable? = null) {
        process(profile, {
            if (!match(options.ids, profile, getId(target))) return@process false
            if (!match(options.targetIds, profile, getTargetId(target))) return@process false
            if (!matchesTarget(profile, target)) return@process false

            val filter = options.locationFilter
            if (filter != null) {
                val loc = getLocation(target) ?: return@process false
                if (!filter.matches(profile, loc)) return@process false
            }

            true
        }) {
            if (event != null && options.isCancelled?.getValue(profile) == true) {
                event.isCancelled = true
            }
        }
    }

    private fun match(
        arg: Argument<List<String>>?,
        profile: OnlineProfile,
        value: String?
    ): Boolean {
        val targetList = arg?.getValue(profile)

        // 디버깅 로그 추가
        println("[AddonDebug] 목표리스트: $targetList | 들어온값: $value")

        if (targetList.isNullOrEmpty()) {
            println("[AddonDebug] -> 목표 리스트가 비어있어서 무조건 통과(true)")
            return true
        }

        if (value == null) {
            println("[AddonDebug] -> 들어온 값이 null이라 실패(false)")
            return false
        }

        val result = matches(targetList, value)
        println("[AddonDebug] -> 매칭 결과: $result")
        return result
    }
}