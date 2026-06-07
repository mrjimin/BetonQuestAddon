//package kr.mrjimin.betonquestaddon.util
//
//import kr.mrjimin.betonquestaddon.util.matcher.WildcardPatternMatcher
//import org.betonquest.betonquest.api.instruction.argument.SimpleArgumentParser
//import java.util.concurrent.ConcurrentHashMap
//
//object WildcardParser : SimpleArgumentParser<List<String>> {
//
//    private val matcherCache =
//        ConcurrentHashMap<List<String>, WildcardPatternMatcher>()
//
//    override fun apply(string: String): List<String> {
//        return string
//            .split(",")
//            .map { it.trim() }
//            .filter { it.isNotEmpty() }
//    }
//
//    fun matches(patterns: List<String>?, value: String?): Boolean {
//        if (patterns.isNullOrEmpty()) return true
//        if (value == null) return false
//
//        val hasWildcard = patterns.any { '*' in it || '?' in it }
//
//        return if (hasWildcard) {
//            val cacheKey = patterns.sorted()
//            matcherCache.getOrPut(cacheKey) {
//                WildcardPatternMatcher(cacheKey)
//            }.matches(value)
//        } else {
//            value in patterns
//        }
//    }
//
//    fun clearCache() {
//        matcherCache.clear()
//    }
//}