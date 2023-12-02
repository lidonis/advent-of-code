package fr.lidonis.adventofcode.y2020.day18

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

internal class OperationOrderTest {

    @ParameterizedTest
    @MethodSource("expressions")
    fun part1(example: Triple<String, Long, Long>) {
        val operationOrder = OperationOrder(example.first.lines())
        assertThat(operationOrder.sum1()).isEqualTo(example.second)
    }

    @ParameterizedTest
    @MethodSource("expressions")
    fun part2(example: Triple<String, Long, Long>) {
        val operationOrder = OperationOrder(example.first.lines())
        assertThat(operationOrder.sum2()).isEqualTo(example.third)
    }

    companion object {

        @JvmStatic
        fun expressions() = listOf(
            Triple("1 + 2", 3, 3),
            Triple("1 + 2 * 3", 9, 9),
            Triple("1 + (2 * 3) + (4 * (5 + 6))", 51, 51),
            Triple("2 * 3 + (4 * 5)", 26, 46),
            Triple("5 + (8 * 3 + 9 + 3 * 4 * 3)", 437, 1445),
            Triple("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))", 12240, 669060),
            Triple("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2", 13632, 23340)
        )
    }
}
