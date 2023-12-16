package fr.lidonis.adventofcode.y2020.day11

import fr.lidonis.adventofcode.common.InputReader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

internal class SeatingSystemTest {

    @ParameterizedTest(name = "Evolve1 after {index} step")
    @MethodSource
    fun evolveAdjacent(evolution: Pair<SeatingSystem, SeatingSystem>) {
        assertThat(evolution.first.evolve()).isEqualTo(evolution.second)
    }

    @Test
    fun stabilizeAdjacent() {
        val seatingSystem = evolveAdjacent().first().first
        assertThat(seatingSystem.stabilize()).isEqualTo(37)
    }

    @ParameterizedTest(name = "Evolve2 after {index} step")
    @MethodSource
    fun evolveFirst(evolution: Pair<SeatingSystem, SeatingSystem>) {
        assertThat(evolution.first.evolve()).isEqualTo(evolution.second)
    }

    @Test
    fun stabilizeFirst() {
        val seatingSystem = evolveFirst().first().first
        assertThat(seatingSystem.stabilize()).isEqualTo(26)
    }

    companion object {

        @JvmStatic
        fun evolveAdjacent() = readLayout("/y2020/day11/part1.txt", ::AdjacentSeatingSystem).zipWithNext()

        @JvmStatic
        fun evolveFirst() = readLayout("/y2020/day11/part2.txt", ::FirstSeatingSystem).zipWithNext()

        private fun readLayout(fileName: String, constructor: (List<String>) -> SeatingSystem) =
            InputReader(fileName).lines()
                .asSequence()
                .filter { it.isNotEmpty() }
                .chunked(10)
                .map(constructor)
                .toList()
    }
}
