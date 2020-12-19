package fr.lidonis.adventofcode.y2020.day19

import fr.lidonis.adventofcode.common.head
import fr.lidonis.adventofcode.common.tail

private val CHAR_RULE_REGEX = Regex("\"\\w\"")

private const val FIRST_RULE_TO_UPDATE = 8
private const val FIRST_RULE_UPDATED = "42 | 42 8"
private const val SECOND_RULE_TO_UPDATE = 11
private const val SECOND_RULE_UPDATED = "42 31 | 42 11 31"

class MonsterMessages(lines: List<String>) {

    private val rules: Map<Int, String>
    private val messages: List<String>

    init {
        val lineIterator = lines.iterator()
        rules = sequence {
            for (line in lineIterator) {
                if (line.isEmpty()) break
                line.split(": ").let { (id, rule) ->
                    yield(id.toInt() to rule)
                }
            }
        }.toMap()
        messages = lineIterator.asSequence().toList()
    }

    fun countValidMessages(): Int {
        val ruleMatcher = RuleMatcher(rules)
        return messages.count { ruleMatcher.matches(it) }
    }

    fun countValidMessagesUpdated(): Int {
        val updatedRules = rules.toMutableMap().also(this::updateRules)
        val ruleMatcher = RuleMatcher(updatedRules)
        return messages.count { ruleMatcher.matches(it) }
    }

    private fun updateRules(rules: MutableMap<Int, String>) {
        rules[FIRST_RULE_TO_UPDATE] = FIRST_RULE_UPDATED
        rules[SECOND_RULE_TO_UPDATE] = SECOND_RULE_UPDATED
    }

    class RuleMatcher(private val rules: Map<Int, String>) {
        fun matches(message: String, rulesToApply: List<Int> = listOf(0)): Boolean {
            return when {
                message.isEmpty() -> rulesToApply.isEmpty()
                rulesToApply.isEmpty() -> false
                else -> {
                    val rule = rules[rulesToApply.head] ?: error("rule not found")
                    if (rule.matches(CHAR_RULE_REGEX)) {
                        return message.startsWith(rule[1]) && matches(message.substring(1), rulesToApply.tail)
                    }
                    rule.split(" | ").any { newRule ->
                        matches(message, newRule.split(" ").map { it.toInt() } + rulesToApply.tail)
                    }
                }
            }
        }
    }
}
