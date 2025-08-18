package com.github.mrjimin.betonquestaddon.compatibility.worldguard

import com.github.mrjimin.betonquestaddon.compatibility.BQAddonIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.worldguard.conditions.WGFlagsFactory
import com.github.mrjimin.betonquestaddon.compatibility.worldguard.conditions.WGHasOwnerFactory
import com.github.mrjimin.betonquestaddon.compatibility.worldguard.conditions.WGIsMemberFactory
import com.github.mrjimin.betonquestaddon.compatibility.worldguard.conditions.WGIsOwnerFactory

object WorldGuardIntegrator : BQAddonIntegrator() {
    override fun hook() {
        condition.apply {
            register("wgIsOwner", WGIsOwnerFactory(data))
            register("wgIsMember", WGIsMemberFactory(data))
            register("wgFlags", WGFlagsFactory(data))
            register("wgHasOwner", WGHasOwnerFactory(data))
        }
    }
}