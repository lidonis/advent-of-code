package fr.lidonis.adventofcode.y2019.day3

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class CrossedWiresTest {

    @Test
    fun distanceExample1() {
        val day3 = CrossedWires(
            "R75,D30,R83,U83,L12,D49,R71,U7,L72",
            "U62,R66,U55,R34,D71,R55,D58,R83"
        )
        assertEquals(159, day3.minimumDistance())
    }

    @Test
    fun distanceExample2() {
        val day3 = CrossedWires(
            "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51",
            "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"
        )
        assertEquals(135, day3.minimumDistance())
    }

    @Test
    fun stepsExample1() {
        val day3 = CrossedWires(
            "R75,D30,R83,U83,L12,D49,R71,U7,L72",
            "U62,R66,U55,R34,D71,R55,D58,R83"
        )
        assertEquals(610, day3.fewestSteps())
    }

    @Test
    fun stepsExample2() {
        val day3 = CrossedWires(
            "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51",
            "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"
        )
        assertEquals(410, day3.fewestSteps())
    }
}
