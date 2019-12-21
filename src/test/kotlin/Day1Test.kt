import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class Day1Test {

    @Test
    fun `fuel requirements for mass of 12`() {
        val spacecraft = Spacecraft(listOf(12))
        Assertions.assertEquals(2, spacecraft.fuelRequirements())
    }

    @Test
    fun `fuel requirements for mass of 14`() {
        val spacecraft = Spacecraft(listOf(14))
        Assertions.assertEquals(2, spacecraft.fuelRequirements())
    }

    @Test
    fun `fuel requirements for mass of 1969`() {
        val spacecraft = Spacecraft(listOf(1969))
        Assertions.assertEquals(654, spacecraft.fuelRequirements())
    }

    @Test
    fun `fuel requirements for mass of 100756`() {
        val spacecraft = Spacecraft(listOf(100756))
        Assertions.assertEquals(33583, spacecraft.fuelRequirements())
    }

    @Test
    fun `fuel requirements for mass of 12 and 14`() {
        val spacecraft = Spacecraft(listOf(12, 14))
        Assertions.assertEquals(4, spacecraft.fuelRequirements())
    }

    @Test
    fun `full fuel requirements for mass of 1969`() {
        val spacecraft = Spacecraft(listOf(1969))
        Assertions.assertEquals(966, spacecraft.fullFuelRequirements())
    }

    @Test
    fun `full fuel requirements for mass of 100756`() {
        val spacecraft = Spacecraft(listOf(100756))
        Assertions.assertEquals(50346, spacecraft.fullFuelRequirements())
    }
}