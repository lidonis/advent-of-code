package fr.lidonis.adventofcode.y2021.day11

private const val FLASH_LIMIT = 9

class DumboOctopus(lines: List<String>) {

    private val linesSize = lines.size

    private val octopuses = IntArray(linesSize * linesSize).apply {
        for ((i, line) in lines.withIndex()) {
            for ((j, c) in line.withIndex()) {
                this[i * linesSize + j] = c.digitToInt()
            }
        }
    }

    fun countFlashes(times: Int): Int {
        var flashes = 0
        repeat(times) {
            flashes += step()
        }
        return flashes
    }

    fun stepToSynchronize(): Int {
        var step = 0
        do {
            val flashes = step()
            step++
        } while (flashes != octopuses.size)
        return step
    }

    private fun step(): Int {
        increaseEnergyLevel()
        val flashes = flash()
        resetFlashedEnergyLevel()
        return flashes
    }

    private fun increaseEnergyLevel() {
        octopuses.indices.forEach { index ->
            octopuses[index]++
        }
    }

    private fun flash(): Int {
        var flashes = 0
        do {
            val indexOf = octopuses.indexOfFirst { it > FLASH_LIMIT }
            if (indexOf != -1) {
                octopuses[indexOf] = -FLASH_LIMIT
                flashes++
                buildAdjacentIndices(indexOf).forEach {
                    octopuses[it]++
                }
            }
        } while (indexOf != -1)
        return flashes
    }

    private fun resetFlashedEnergyLevel() {
        for (index in octopuses.indices) {
            octopuses[index] = if (octopuses[index] < 0) 0 else octopuses[index]
        }
    }

    private fun buildAdjacentIndices(index: Int): List<Int> = buildList {
        if (index % linesSize != 0) {
            add(index - linesSize - 1)
            add(index - 1)
            add(index + linesSize - 1)
        }
        if (index % linesSize != linesSize - 1) {
            add(index - linesSize + 1)
            add(index + 1)
            add(index + linesSize + 1)
        }
        add(index - linesSize)
        add(index + linesSize)
    }.filter { it in octopuses.indices }
}
