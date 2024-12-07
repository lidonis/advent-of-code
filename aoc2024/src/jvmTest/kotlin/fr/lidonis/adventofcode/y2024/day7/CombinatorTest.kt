package fr.lidonis.adventofcode.y2024.day7

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CombinatorTest {

    @Test
    fun `combine list of 1`() {
        val numbers = listOf<Long>(1)
        val result = Combinator.combine(numbers, listOf(Operation.ADD, Operation.MULTIPLY))
        assertThat(result).isEqualTo(listOf<Long>(1))
    }

    @Test
    fun `combine list of 2`() {
        val numbers = listOf<Long>(5, 8)
        val result = Combinator.combine(numbers, listOf(Operation.ADD, Operation.MULTIPLY))
        assertThat(result).isEqualTo(listOf<Long>(13, 40))
    }

    @Test
    fun `combine list of 3`() {
        val numbers = listOf<Long>(5, 8, 10)
        val result = Combinator.combine(numbers, listOf(Operation.ADD, Operation.MULTIPLY))
        assertThat(result).isEqualTo(listOf<Long>(23, 130, 50, 400))
    }

    @Test
    fun `combine list of 2 with concatenation`() {
        val numbers = listOf<Long>(5, 8)
        val result = Combinator.combine(numbers, Operation.entries)
        assertThat(result).isEqualTo(listOf<Long>(13, 40, 58))
    }
}
