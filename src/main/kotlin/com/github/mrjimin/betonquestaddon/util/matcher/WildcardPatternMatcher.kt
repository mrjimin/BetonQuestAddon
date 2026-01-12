package com.github.mrjimin.betonquestaddon.util.matcher

import java.util.regex.Pattern

class WildcardPatternMatcher(patterns: List<String>) : StringMatcher {

    private val combinedPattern: Pattern = patterns
        .takeIf { it.isNotEmpty() }
        ?.joinToString("|") { raw ->
            raw.split('*', '?')
                .joinToString(separator = "") { Pattern.quote(it) }
                .let {
                    raw.replace("?", ".").replace("*", ".*")
                }
                .let { "^$it$" }
        }?.let { Pattern.compile(it) }
        ?: Pattern.compile("^$")

    override fun matches(input: String): Boolean = combinedPattern.matcher(input).matches()
}