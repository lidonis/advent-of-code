package fr.lidonis.adventofcode.y2023.day9

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MirageMaintenanceTest {

    private val input = """
        0 3 6 9 12 15
        1 3 6 10 15 21
        10 13 16 21 30 45
    """.trimIndent()

    @Test
    fun part1() {
        val mirageMaintenance = MirageMaintenance(input.lines())
        val result = mirageMaintenance.part1()
        assertThat(result).isEqualTo(114)
    }

    @Test
    fun part2() {
        val mirageMaintenance = MirageMaintenance(input.lines())
        val result = mirageMaintenance.part2()
        assertThat(result).isEqualTo(2)
    }
}
