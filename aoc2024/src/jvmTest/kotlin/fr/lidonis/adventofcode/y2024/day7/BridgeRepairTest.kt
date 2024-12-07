package fr.lidonis.adventofcode.y2024.day7

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BridgeRepairTest {

    private val input = """
        190: 10 19
        3267: 81 40 27
        83: 17 5
        156: 15 6
        7290: 6 8 6 15
        161011: 16 10 13
        192: 17 8 14
        21037: 9 7 18 13
        292: 11 6 16 20
    """.trimIndent()

    @Test
    fun part1() {
        val bridgeRepair = BridgeRepair(input.lines())
        val result = bridgeRepair.part1()
        assertThat(result).isEqualTo(3749)
    }

    @Test
    fun part2() {
        val bridgeRepair = BridgeRepair(input.lines())
        val result = bridgeRepair.part2()
        assertThat(result).isEqualTo(11387)
    }
}
