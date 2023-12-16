package fr.lidonis.adventofcode.y2023.day8

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HauntedWastelandTest {

    val input = """
        RL

        AAA = (BBB, CCC)
        BBB = (DDD, EEE)
        CCC = (ZZZ, GGG)
        DDD = (DDD, DDD)
        EEE = (EEE, EEE)
        GGG = (GGG, GGG)
        ZZZ = (ZZZ, ZZZ)
    """.trimIndent()

    @Test
    fun part1() {
        val hauntedWasteland = HauntedWasteland(input.lines())
        val result = hauntedWasteland.part1()
        assertThat(result).isEqualTo(2)
    }

    val otherInput = """
        LLR
        
        AAA = (BBB, BBB)
        BBB = (AAA, ZZZ)
        ZZZ = (ZZZ, ZZZ)
    """.trimIndent()

    @Test
    fun part1Other() {
        val hauntedWasteland = HauntedWasteland(otherInput.lines())
        val result = hauntedWasteland.part1()
        assertThat(result).isEqualTo(6)
    }

    val ghostInput = """
        LR

        11A = (11B, XXX)
        11B = (XXX, 11Z)
        11Z = (11B, XXX)
        22A = (22B, XXX)
        22B = (22C, 22C)
        22C = (22Z, 22Z)
        22Z = (22B, 22B)
        XXX = (XXX, XXX)
    """.trimIndent()

    @Test
    fun part2() {
        val hauntedWasteland = HauntedWasteland(ghostInput.lines())
        val result = hauntedWasteland.part2()
        assertThat(result).isEqualTo(6)
    }
}
