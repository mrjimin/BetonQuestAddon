package kr.mrjimin.betonquestaddon.util.matcher

import java.util.regex.Pattern

class WildcardPatternMatcher(patterns: List<String>) : StringMatcher {

    private val combinedPattern: Pattern = if (patterns.isEmpty()) {
        Pattern.compile("^$")
    } else {
        val regexString = patterns.joinToString(separator = "|", prefix = "^(", postfix = ")$") { raw ->
            Pattern.quote(raw)
                .replace("*", "\\E.*\\Q")
                .replace("?", "\\E.\\Q")
                .replace("\\Q\\E", "")
        }
        Pattern.compile(regexString)
    }

    override fun matches(input: String): Boolean = combinedPattern.matcher(input).matches()
}