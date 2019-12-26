import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputer
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class Day9Test {

    @Test
    fun `example 1`() {
        val program = listOf(109L, 1, 204, -1, 1001, 100, 1, 100, 1008, 100, 16, 101, 1006, 101, 0, 99)
        val computer = IntCodeComputer(program)
        computer.asSequence().last()
        Assertions.assertEquals(program, computer.outputs.map { it })
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
        Assertions.assertEquals(34915192L * 34915192, computer.nextOutput())
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
        Assertions.assertEquals(1125899906842624, computer.nextOutput())
    }

    @Test
    fun `day 9`() {
        val program = InputReader("day9.txt").asLineOfLongs()
        val computer = IntCodeComputer(program)
        computer.input(1)
        Assertions.assertEquals(3601950151, computer.nextOutput())
        computer.reset()
        computer.input(2)
        Assertions.assertEquals(64236L, computer.nextOutput())
    }
}