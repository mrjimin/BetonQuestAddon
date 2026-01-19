package com.github.mrjimin.betonquestaddon.util

import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.entity.Player

fun Player.playParticle(particle: Particle, location: Location) {
    this.spawnParticle(particle, location.x, location.y, location.z, 1, 0.0, 0.0, 0.0, 0.0, null, false)
}

fun Player.playParticle(particle: Particle, x: Double, y: Double, z: Double) {
    this.spawnParticle(particle, x, y, z, 1, 0.0, 0.0, 0.0, 0.0, null, false)
}

fun Player.drawCube(
    particle: Particle,
    location: Location,
    width: Double,
    height: Double,
    interval: Double = 0.125
) {
    val halfW = width / 2.0

    val aabb = AABB(
        minX = location.x - halfW,
        minY = location.y,
        minZ = location.z - halfW,
        maxX = location.x + halfW,
        maxY = location.y + height,
        maxZ = location.z + halfW
    )

    val points = aabb.getEdgePoints(interval)

    for (point in points) {
        this.playParticle(particle, point.x, point.y, point.z)
    }
}