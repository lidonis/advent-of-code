import org.junit.Assert
import org.junit.Test

internal class Day10Test {

    @Test
    fun `example 1`() {
        val input = ".#..#\n" +
                ".....\n" +
                "#####\n" +
                "....#\n" +
                "...##"
        val map = AsteroidMap(input.split("\n"))
        Assert.assertEquals(8, map.bestMonitoringStation())
    }
}