package fr.lidonis.adventofcode.y2020.day7

private val REGEX_LINES = Regex("""([\w\s]+) bags contain ([\d\w\s,]+)\.""")

class LuggageProcessing(rules: List<String>) {

    private val bags = rules
        .map { REGEX_LINES.matchEntire(it)!!.destructured }
        .map { (a, b) -> Bag.parseBag(a, b) }

    fun countCanContain(color: String) = recurseUp(color).size
    fun requiredBagCount(color: String) = recurseDown(color) - 1

    private fun recurseUp(color: String): List<Bag> = bags
        .filter { bag -> bag.containedColors.any { it.second == color } }
        .let { list -> list + list.flatMap { recurseUp(it.color) } }
        .distinct()

    private fun recurseDown(color: String): Int = 1 + bags
        .first { it.color == color }.containedColors
        .sumOf { (c, bag) -> c * recurseDown(bag) }

    data class Bag(val color: String, val containedColors: List<Pair<Int, String>> = emptyList()) {
        companion object {
            private val REGEX_BAG = Regex("""(\d+) ([\w ]+) bags?""")
            private const val NO_OTHER_BAG = "no other bags"

            fun parseBag(color: String, rule: String) = Bag(color, parseRule(rule))

            private fun parseRule(rule: String): List<Pair<Int, String>> {
                return if (rule == NO_OTHER_BAG) {
                    emptyList()
                } else {
                    readRules(rule)
                }
            }

            private fun readRules(rule: String) = rule.split(", ").map {
                REGEX_BAG.matchEntire(it)!!.destructured
                    .let { (a, b) -> a.toInt() to b }
            }
        }
    }
}
