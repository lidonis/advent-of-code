import org.junit.Assert
import org.junit.Test

internal class Day1Test {

    @Test
    fun `fuel requirements for mass of 12`() {
        val spacecraft = Spacecraft(listOf(12))
        Assert.assertEquals(2, spacecraft.fuelRequirements())
    }

    @Test
    fun `fuel requirements for mass of 14`() {
        val spacecraft = Spacecraft(listOf(14))
        Assert.assertEquals(2, spacecraft.fuelRequirements())
    }

    @Test
    fun `fuel requirements for mass of 1969`() {
        val spacecraft = Spacecraft(listOf(1969))
        Assert.assertEquals(654, spacecraft.fuelRequirements())
    }

    @Test
    fun `fuel requirements for mass of 100756`() {
        val spacecraft = Spacecraft(listOf(100756))
        Assert.assertEquals(33583, spacecraft.fuelRequirements())
    }

    @Test
    fun `fuel requirements for mass of 12 and 14`() {
        val spacecraft = Spacecraft(listOf(12, 14))
        Assert.assertEquals(4, spacecraft.fuelRequirements())
    }

    @Test
    fun `full fuel requirements for mass of 1969`() {
        val spacecraft = Spacecraft(listOf(1969))
        Assert.assertEquals(966, spacecraft.fullFuelRequirements())
    }

    @Test
    fun `full fuel requirements for mass of 100756`() {
        val spacecraft = Spacecraft(listOf(100756))
        Assert.assertEquals(50346, spacecraft.fullFuelRequirements())
    }
}