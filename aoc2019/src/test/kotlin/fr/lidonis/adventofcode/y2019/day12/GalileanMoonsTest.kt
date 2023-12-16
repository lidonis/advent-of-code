package fr.lidonis.adventofcode.y2019.day12

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class GalileanMoonsTest {

    private val scan1 = """
        <x=-1, y=0, z=2>
        <x=2, y=-10, z=-7>
        <x=4, y=-8, z=8>
        <x=3, y=5, z=-1>
    """.trimIndent().lines()

    @Test
    fun totalEnergy1() {
        Assertions.assertThat(GalileanMoons(scan1).totalEnergy(10)).isEqualTo(179)
    }

    private val scan2 = """
        <x=-8, y=-10, z=0>
        <x=5, y=5, z=10>
        <x=2, y=-7, z=3>
        <x=9, y=-8, z=-3>
    """.trimIndent().lines()

    @Test
    fun totalEnergy2() {
        Assertions.assertThat(GalileanMoons(scan2).totalEnergy(100)).isEqualTo(1940)
    }

    @Test
    fun stepsToRepeatPreviousState1() {
        Assertions.assertThat(GalileanMoons(scan1).stepsToRepeatPreviousState()).isEqualTo(2772)
    }

    @Test
    fun stepsToRepeatPreviousState2() {
        Assertions.assertThat(GalileanMoons(scan2).stepsToRepeatPreviousState()).isEqualTo(4686774924)
    }
}
