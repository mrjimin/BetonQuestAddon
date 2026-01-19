package com.github.mrjimin.betonquestaddon.util

import kotlin.math.ceil
import kotlin.math.pow
import kotlin.math.sqrt

data class Vec3d(val x: Double, val y: Double, val z: Double) {
    fun distanceTo(other: Vec3d): Double {
        return sqrt((x - other.x).pow(2) + (y - other.y).pow(2) + (z - other.z).pow(2))
    }

    private fun Double.pow(n: Int) = this.pow(n.toDouble())
}

data class AABB(
    val minX: Double, val minY: Double, val minZ: Double,
    val maxX: Double, val maxY: Double, val maxZ: Double
) {

    fun getEdgePoints(interval: Double): List<Vec3d> {
        val points = mutableListOf<Vec3d>()

        val v = arrayOf(
            Vec3d(minX, minY, minZ), // 0
            Vec3d(maxX, minY, minZ), // 1
            Vec3d(minX, maxY, minZ), // 2
            Vec3d(maxX, maxY, minZ), // 3
            Vec3d(minX, minY, maxZ), // 4
            Vec3d(maxX, minY, maxZ), // 5
            Vec3d(minX, maxY, maxZ), // 6
            Vec3d(maxX, maxY, maxZ)  // 7
        )

        val edges = arrayOf(
            0 to 1, 1 to 3, 3 to 2, 2 to 0,
            4 to 5, 5 to 7, 7 to 6, 6 to 4,
            0 to 4, 1 to 5, 2 to 6, 3 to 7
        )

        for ((startIdx, endIdx) in edges) {
            points.addAll(sampleLine(v[startIdx], v[endIdx], interval))
        }

        return points.distinct()
    }

    private fun sampleLine(start: Vec3d, end: Vec3d, interval: Double): List<Vec3d> {
        val linePoints = mutableListOf<Vec3d>()

        val dx = end.x - start.x
        val dy = end.y - start.y
        val dz = end.z - start.z

        val length = sqrt(dx * dx + dy * dy + dz * dz)
        if (length == 0.0) return listOf(start)

        val samples = ceil(length / interval).toInt()

        for (i in 0..samples) {
            val t = i.toDouble() / samples
            linePoints.add(
                Vec3d(
                    start.x + dx * t,
                    start.y + dy * t,
                    start.z + dz * t
                )
            )
        }
        return linePoints
    }
}