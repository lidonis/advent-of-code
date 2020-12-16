package fr.lidonis.adventofcode.y2020.day10

private const val MAX_DIFFERENCE = 3

class AdapterArray(list: List<Int>) {

    private val adapters = list.sortedDescending() + 0

    fun differences() = adapters
        .zipWithNext()
        .map { it.first - it.second }
        .groupingBy { it }
        .eachCount()
        .let { (it[1] ?: 0) * (1 + (it[MAX_DIFFERENCE] ?: 0)) }

    fun arrangements(): Long {
        val cache = HashMap<Int, Long>()
        cache[adapters.first()] = 1
        adapters.drop(1).forEach { jolts ->
            cache[jolts] = (jolts + 1..jolts + MAX_DIFFERENCE).map { cache[it] ?: 0 }.sum()
        }
        return cache[0] ?: 0
    }
}

