package fr.lidonis.adventofcode.y2019.day11

import fr.lidonis.adventofcode.y2019.day11.EmergencyHullPaintingRobot.Color.BLACK
import fr.lidonis.adventofcode.y2019.intcodecomputer.IOCodeComputer
import io.mockk.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EmergencyHullPaintingRobotTest {

    @Test
    fun `example 1`() {
        val computer = mockk<IOCodeComputer>()
        val robot = EmergencyHullPaintingRobot(computer, BLACK)

        every { computer.hasNext() } returnsMany List(7) { true } + listOf(false)
        every { computer.nextOutput() } returnsMany listOf(1L, 0, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0)
        every { computer.input(ofType(Long::class)) } just Runs

        robot.compute()

        verifyOrder {
            computer.input(0)
            computer.input(0)
            computer.input(0)
            computer.input(0)
            computer.input(1)
            computer.input(0)
            computer.input(0)
        }

        assertThat(robot.panelsPaintedAtLeastOnce).isEqualTo(6)
    }
}