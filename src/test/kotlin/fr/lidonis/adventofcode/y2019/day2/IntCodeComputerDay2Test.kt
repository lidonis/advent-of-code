package fr.lidonis.adventofcode.y2019.day2

import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputerFactory
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

@Tag("IntCodeComputer")
internal class IntCodeComputerDay2Test {

    @Test
    fun `program 1`() {
        val computer = IntCodeComputerFactory.buildBasicComputer("1,0,0,0,99")
        Assertions.assertEquals(listOf(2L, 0, 0, 0, 99), computer.next().memory)
        Assertions.assertFalse(computer.hasNext())
    }

    @Test
    fun `program 2`() {
        val computer = IntCodeComputerFactory.buildBasicComputer("2,3,0,3,99")
        Assertions.assertEquals(listOf(2L, 3, 0, 6, 99), computer.next().memory)
        Assertions.assertFalse(computer.hasNext())
    }

    @Test
    fun `program 3`() {
        val computer = IntCodeComputerFactory.buildBasicComputer("2,4,4,5,99,0")
        Assertions.assertEquals(listOf(2L, 4, 4, 5, 99, 9801), computer.next().memory)
        Assertions.assertFalse(computer.hasNext())
    }

    @Test
    fun `program 4`() {
        val computer = IntCodeComputerFactory.buildBasicComputer("1,1,1,4,99,5,6,0,99")
        computer.run()
        Assertions.assertEquals(listOf(30L, 1, 1, 4, 2, 5, 6, 0, 99), computer.memory)
        Assertions.assertFalse(computer.hasNext())
    }

    @Test
    fun `program 5`() {
        val computer = IntCodeComputerFactory.buildBasicComputer("1,9,10,3,2,3,11,0,99,30,40,50")
        Assertions.assertEquals(
            listOf(
                1L, 9, 10, 70,
                2, 3, 11, 0,
                99,
                30, 40, 50
            ), computer.next().memory
        )
        Assertions.assertEquals(
            listOf(
                3500L, 9, 10, 70,
                2, 3, 11, 0,
                99,
                30, 40, 50
            ), computer.next().memory
        )
        Assertions.assertFalse(computer.hasNext())
    }
}
