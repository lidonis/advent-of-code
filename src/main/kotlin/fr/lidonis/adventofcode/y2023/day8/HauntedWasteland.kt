package fr.lidonis.adventofcode.y2023.day8

import fr.lidonis.adventofcode.common.math.lcm

private val PARSE_REGEXP = """^(\w+)\s*=\s*\((\w+),\s*(\w+)\)${'$'}""".toRegex()

class HauntedWasteland(lines: List<String>) {

    private val instructions = lines.first()
    private val network = lines
        .drop(2)
        .mapNotNull { PARSE_REGEXP.matchEntire(it)?.destructured }
        .associate { (start, leftNode, rightNode) ->
            start to (leftNode to rightNode)
        }

    fun part1(): Int {
        return navigateWasteland("AAA") { it == "ZZZ" }
    }

    private fun navigateWasteland(startLocation: String, stopCondition: (String) -> Boolean): Int {
        var node = startLocation
        var step = 0

        instructions.infiniteLoop().forEach { instruction ->
            val (leftNode, rightNode) = network[node]!!
            node = when (instruction) {
                'L' -> leftNode
                'R' -> rightNode
                else -> error("Unknown instruction $instruction")
            }
            step++
            if (stopCondition(node)) return step
        }

        error("No path found for $startLocation")
    }

    private fun String.infiniteLoop() = sequence {
        while (true) {
            this.yieldAll(this@infiniteLoop.toList())
        }
    }

    fun part2(): Long {
        return network.keys
            .filter { it.endsWith('A') }
            .map { node -> navigateWasteland(node) { it.endsWith('Z') }.toLong() }
            .reduce { acc, i -> lcm(acc, i) }
    }
}
