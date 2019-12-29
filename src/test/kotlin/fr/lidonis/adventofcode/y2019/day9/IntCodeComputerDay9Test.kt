package fr.lidonis.adventofcode.y2019.day9

import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

@Tag("IntCodeComputer")
class IntCodeComputerDay9Test {

    @Test
    fun `example 1`() {
        val program = listOf(109L, 1, 204, -1, 1001, 100, 1, 100, 1008, 100, 16, 101, 1006, 101, 0, 99)
        val computer = IntCodeComputer(program)
        computer.run()
        assertThat(computer.outputs.map { it }).isEqualTo(program)
    }

    @Test
    fun `example 2`() {
        val computer = IntCodeComputer(
            listOf(
                1102,
                34915192,
                34915192,
                7,
                4,
                7,
                99,
                0
            )
        )
        assertThat(computer.nextOutput()).isEqualTo(34915192L * 34915192)
    }

    @Test
    fun `example 3`() {
        val computer = IntCodeComputer(
            listOf(
                104,
                1125899906842624,
                99
            )
        )
        assertThat(computer.nextOutput()).isEqualTo(1125899906842624)
    }
}