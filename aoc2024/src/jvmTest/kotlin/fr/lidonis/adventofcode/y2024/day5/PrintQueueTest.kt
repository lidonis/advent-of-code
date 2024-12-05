package fr.lidonis.adventofcode.y2024.day5

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PrintQueueTest {

    private val input = """
        47|53
        97|13
        97|61
        97|47
        75|29
        61|13
        75|53
        29|13
        97|29
        53|29
        61|53
        97|53
        61|29
        47|13
        75|47
        97|75
        47|61
        75|61
        47|29
        75|13
        53|13

        75,47,61,53,29
        97,61,53,29,13
        75,29,13
        75,97,47,61,53
        61,13,29
        97,13,75,29,47
    """.trimIndent()

    @Test
    fun part1() {
        val printQueue = PrintQueue(input.lines())
        val result = printQueue.part1()
        assertThat(result).isEqualTo(143)
    }

    @Test
    fun part2() {
        val printQueue = PrintQueue(input.lines())
        val result = printQueue.part2()
        assertThat(result).isEqualTo(123)
    }
}
