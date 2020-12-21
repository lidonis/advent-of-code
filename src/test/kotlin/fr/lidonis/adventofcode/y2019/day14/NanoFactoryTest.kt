package fr.lidonis.adventofcode.y2019.day14

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class NanoFactoryTest {

    @Test
    fun `1 fuel from 1 ore`() {
        val input = "1 ORE => 1 FUEL"
        val factory = NanoFactory(input)
        Assertions.assertEquals(1, factory.minimumOre())
    }

    @Test
    fun `10 ore`() {
        val input = "10 ORE => 1 FUEL"
        val factory = NanoFactory(input)
        Assertions.assertEquals(10, factory.minimumOre())
    }

    //@Test
    fun `intermediary product`() {
        val input = "10 ORE => 1 A\n" +
                "2 A => 1 FUEL"
        val factory = NanoFactory(input)
        Assertions.assertEquals(20, factory.minimumOre())
    }

    //@Test
    fun `produce 2 intermediary product`() {
        val input = "10 ORE => 2 A\n" +
                "2 A => 1 FUEL"
        val factory = NanoFactory(input)
        Assertions.assertEquals(10, factory.minimumOre())
    }

    //@Test
    fun `multiple input reaction`() {
        val input = "1 ORE => 1 A\n" +
                "1 ORE => 1 B\n" +
                "1 A, 1 B => 1 FUEL"
        val factory = NanoFactory(input)
        Assertions.assertEquals(2, factory.minimumOre())
    }

    //@Test
    fun `left over`() {
        val input = "10 ORE => 10 A\n" +
                "7 A => 1 FUEL"
        val factory = NanoFactory(input)
        Assertions.assertEquals(10, factory.minimumOre())
    }

    //@Test
    fun `example 1`() {
        val input = "10 ORE => 10 A\n" +
                "1 ORE => 1 B\n" +
                "7 A, 1 B => 1 C\n" +
                "7 A, 1 C => 1 D\n" +
                "7 A, 1 D => 1 E\n" +
                "7 A, 1 E => 1 FUEL"

        val factory = NanoFactory(input)
        Assertions.assertEquals(31, factory.minimumOre())
    }

}
