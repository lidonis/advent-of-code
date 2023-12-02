package fr.lidonis.adventofcode.y2021.day14

class ExtendedPolymerization(lines: List<String>) {

    private val polymerTemplate = lines.first()

    private val pairInsertionRules = lines.drop(2)
        .map { it.split(" -> ") }
        .associate { (a, b) -> a to b }

    fun mostAndLeastCommonElements(nbSteps: Int): Long {
        val (pairs, elements) = with(polymerTemplate) { windowed(2).frequencies() to map(Char::toString).frequencies() }

        repeat(nbSteps) {
            val newPairs = mutableMapOf<String, Long>()
            for ((rulePair, element) in pairInsertionRules) {
                pairs.entries.filter { it.key == rulePair }.forEach { (pair, count) ->
                    pairs[pair] = 0
                    newPairs.inc(pair[0] + element, count)
                    newPairs.inc(element + pair[1], count)
                    elements.inc(element, count)
                }
            }
            newPairs.forEach { pairs.inc(it.key, it.value) }
        }

        return with(elements.values) { maxOf { it } - minOf { it } }
    }
}

fun <K> Iterable<K>.frequencies() = mutableMapOf<K, Long>().apply { this@frequencies.forEach { inc(it, 1) } }
fun <K> MutableMap<K, Long>.inc(k: K, n: Long) = set(k, get(k)?.let { it + n } ?: n)
