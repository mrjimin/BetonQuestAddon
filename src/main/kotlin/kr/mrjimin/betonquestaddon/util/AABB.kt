package kr.mrjimin.betonquestaddon.util

import kotlin.math.ceil

data class Vector3(val x: Double, val y: Double, val z: Double)

data class AABB(
    val minX: Double, val minY: Double, val minZ: Double,
    val maxX: Double, val maxY: Double, val maxZ: Double
) {

    fun getEdgePoints(interval: Double): List<Vector3> {
        val points = mutableListOf<Vector3>()

        for (x in listOf(minX, maxX))
            for (y in listOf(minY, maxY))
                for (z in listOf(minZ, maxZ))
                    points.add(Vector3(x, y, z))

        addLinePoints(points, maxX - minX, interval) { d -> listOf(Vector3(minX + d, minY, minZ), Vector3(minX + d, maxY, minZ), Vector3(minX + d, minY, maxZ), Vector3(minX + d, maxY, maxZ)) } // X축
        addLinePoints(points, maxY - minY, interval) { d -> listOf(Vector3(minX, minY + d, minZ), Vector3(maxX, minY + d, minZ), Vector3(minX, minY + d, maxZ), Vector3(maxX, minY + d, maxZ)) } // Y축
        addLinePoints(points, maxZ - minZ, interval) { d -> listOf(Vector3(minX, minY, minZ + d), Vector3(maxX, minY, minZ + d), Vector3(minX, maxY, minZ + d), Vector3(maxX, maxY, minZ + d)) } // Z축

        return points
    }

    private inline fun addLinePoints(points: MutableList<Vector3>, length: Double, interval: Double, provider: (Double) -> List<Vector3>) {
        if (length <= interval) return
        val samples = ceil(length / interval).toInt()
        for (i in 1 until samples) {
            points.addAll(provider(length * i / samples))
        }
    }

}