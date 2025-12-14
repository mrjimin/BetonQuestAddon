package com.github.mrjimin.betonquestaddon.compatibilityold.worldguard

import com.github.mrjimin.betonquestaddon.compatibilityold.BQAddonIntegrator
import com.github.mrjimin.betonquestaddon.compatibilityold.worldguard.conditions.WGFlagsFactory
import com.github.mrjimin.betonquestaddon.compatibilityold.worldguard.conditions.WGHasMemberFactory
import com.github.mrjimin.betonquestaddon.compatibilityold.worldguard.conditions.WGHasOwnerFactory
import com.github.mrjimin.betonquestaddon.compatibilityold.worldguard.conditions.WGIsMemberFactory
import com.github.mrjimin.betonquestaddon.compatibilityold.worldguard.conditions.WGIsOwnerFactory

object WorldGuardIntegrator : BQAddonIntegrator() {
    override fun hook() {
        condition.apply {
            register("wgIsOwner", WGIsOwnerFactory(data))
            register("wgIsMember", WGIsMemberFactory(data))
            register("wgFlags", WGFlagsFactory(data))
            register("wgHasOwner", WGHasOwnerFactory(data))
            register("wgHasMember", WGHasMemberFactory(data))
        }
    }
}