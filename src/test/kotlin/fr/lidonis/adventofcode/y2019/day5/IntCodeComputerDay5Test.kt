package fr.lidonis.adventofcode.y2019.day5

import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputerFactory
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

@Tag("IntCodeComputer")
class IntCodeComputerDay5Test {

    @Test
    fun `test input`() {
        val computer = IntCodeComputerFactory.buildIOComputer("3,1")
        computer.input(10)
        Assertions.assertEquals(listOf(3L, 10), computer.next().memory)
    }

    @Test
    fun `test output`() {
        val computer = IntCodeComputerFactory.buildIOComputer("4,1")
        Assertions.assertEquals(1L, computer.nextOutput())
    }

    private val equalPositionProgram = "3,9,8,9,10,9,4,9,99,-1,8"

    @Test
    fun `test not equal 8 with position mode`() {
        val computer = IntCodeComputerFactory.buildIOComputer(equalPositionProgram)
        computer.input(1)
        Assertions.assertEquals(0L, computer.nextOutput())
    }

    @Test
    fun `test equals 8 with position mode`() {
        val computer = IntCodeComputerFactory.buildIOComputer(equalPositionProgram)
        computer.input(8)
        Assertions.assertEquals(1L, computer.nextOutput())
    }

    private val lessPositionProgram = "3,9,7,9,10,9,4,9,99,-1,8"

    @Test
    fun `test less than 8 with position mode`() {
        val computer = IntCodeComputerFactory.buildIOComputer(lessPositionProgram)
        computer.input(5)
        Assertions.assertEquals(1L, computer.nextOutput())
    }

    @Test
    fun `test not less than 8 with position mode`() {
        val computer = IntCodeComputerFactory.buildIOComputer(lessPositionProgram)
        computer.input(8)
        Assertions.assertEquals(0L, computer.nextOutput())
    }


    private val equalImmediateProgram = "3,3,1108,-1,8,3,4,3,99"

    @Test
    fun `test not equal 8 with immediate mode`() {
        val computer = IntCodeComputerFactory.buildIOComputer(equalImmediateProgram)
        computer.input(1)
        Assertions.assertEquals(0L, computer.nextOutput())
    }

    @Test
    fun `test equals 8 with immediate mode`() {
        val computer = IntCodeComputerFactory.buildIOComputer(equalImmediateProgram)
        computer.input(8)
        Assertions.assertEquals(1L, computer.nextOutput())
    }

    private val lessImmediateProgram = "3,3,1107,-1,8,3,4,3,99"

    @Test
    fun `test less than 8 with immediate mode`() {
        val computer = IntCodeComputerFactory.buildIOComputer(lessImmediateProgram)
        computer.input(5)
        Assertions.assertEquals(1L, computer.nextOutput())
    }

    @Test
    fun `test not less than 8 with immediate mode`() {
        val computer = IntCodeComputerFactory.buildIOComputer(lessImmediateProgram)
        computer.input(8)
        Assertions.assertEquals(0L, computer.nextOutput())
    }

    private val jumpProgram = "3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9"

    @Test
    fun `test jump with zero position mode`() {
        val computer = IntCodeComputerFactory.buildIOComputer(jumpProgram)
        computer.input(0)
        Assertions.assertEquals(0L, computer.nextOutput())
    }

    @Test
    fun `test jump with non zero position mode`() {
        val computer = IntCodeComputerFactory.buildIOComputer(jumpProgram)
        computer.input(-6)
        Assertions.assertEquals(1L, computer.nextOutput())
    }

    private val jump2Program = "3,3,1105,-1,9,1101,0,0,12,4,12,99,1"

    @Test
    fun `test 2 jump with zero position mode`() {
        val computer = IntCodeComputerFactory.buildIOComputer(jump2Program)
        computer.input(0)
        Assertions.assertEquals(0L, computer.nextOutput())
    }

    @Test
    fun `test 2 jump with non zero position mode`() {
        val computer = IntCodeComputerFactory.buildIOComputer(jump2Program)
        computer.input(-6)
        Assertions.assertEquals(1L, computer.nextOutput())
    }

    private val largeProgram = "3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31," +
            "1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104," +
            "999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99"


    @Test
    internal fun `test large program less that 8`() {
        val computer = IntCodeComputerFactory.buildIOComputer(largeProgram)
        computer.input(7)
        Assertions.assertEquals(999L, computer.nextOutput())
    }

    @Test
    internal fun `test large program equal that 8`() {
        val computer = IntCodeComputerFactory.buildIOComputer(largeProgram)
        computer.input(8)
        Assertions.assertEquals(1000L, computer.nextOutput())
    }

    @Test
    internal fun `test large program more that 8`() {
        val computer = IntCodeComputerFactory.buildIOComputer(largeProgram)
        computer.input(9)
        Assertions.assertEquals(1001L, computer.nextOutput())
    }
}