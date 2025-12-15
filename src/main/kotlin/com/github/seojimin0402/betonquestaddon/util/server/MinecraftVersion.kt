package com.github.seojimin0402.betonquestaddon.util.server

import org.bukkit.Bukkit
import java.util.regex.Pattern

enum class MinecraftVersion(val versionString: String) {
    V1_21_1("1.21.1"),
    V1_21_3("1.21.3"),
    V1_21_4("1.21.4"),
    V1_21_8("1.21.8");

    companion object {
        private val VERSION_PATTERN = Pattern.compile("1\\.(19|20|21)(\\.\\d+)?")

        val rawVersion: String? by lazy {
            VERSION_PATTERN.matcher(Bukkit.getBukkitVersion()).takeIf { it.find() }?.group()
        }

        val current: MinecraftVersion? by lazy {
            val raw = rawVersion ?: return@lazy null
            entries.sortedWith { a, b -> compare(b.versionString, a.versionString) }
                .find { it.isAtOrBelow(raw) }
        }

        fun compare(a: String, b: String): Int {
            val partsA = a.split(".", "-").mapNotNull { it.toIntOrNull() }
            val partsB = b.split(".", "-").mapNotNull { it.toIntOrNull() }
            val length = maxOf(partsA.size, partsB.size)
            for (i in 0 until length) {
                val diff = partsA.getOrElse(i) { 0 } - partsB.getOrElse(i) { 0 }
                if (diff != 0) return diff
            }
            return 0
        }
    }

    fun isExact(): Boolean = rawVersion == versionString
    fun isAtOrAbove(): Boolean = rawVersion?.let { compare(it, versionString) >= 0 } ?: false
    fun isAtOrBelow(): Boolean = rawVersion?.let { compare(it, versionString) <= 0 } ?: false
    fun isAbove(): Boolean = rawVersion?.let { compare(it, versionString) > 0 } ?: false
    fun isBelow(): Boolean = rawVersion?.let { compare(it, versionString) < 0 } ?: false
    fun isAtOrAbove(other: String): Boolean = compare(versionString, other) >= 0
    fun isAtOrBelow(other: String): Boolean = compare(versionString, other) <= 0
}
