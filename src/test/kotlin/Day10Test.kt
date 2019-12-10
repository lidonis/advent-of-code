import AsteroidMap.*
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
        val map = AsteroidMap(input)
        Assert.assertEquals(8, map.bestMonitoringStation())
    }

    @Test
    fun `example 2`() {
        val input = "......#.#.\n" +
                "#..#.#....\n" +
                "..#######.\n" +
                ".#.#.###..\n" +
                ".#..#.....\n" +
                "..#....#.#\n" +
                "#..#....#.\n" +
                ".##.#..###\n" +
                "##...#..#.\n" +
                ".#....####"
        val map = AsteroidMap(input)
        Assert.assertEquals(33, map.bestMonitoringStation())
    }

    @Test
    fun `example 3`() {
        val input = "#.#...#.#.\n" +
                ".###....#.\n" +
                ".#....#...\n" +
                "##.#.#.#.#\n" +
                "....#.#.#.\n" +
                ".##..###.#\n" +
                "..#...##..\n" +
                "..##....##\n" +
                "......#...\n" +
                ".####.###."
        val map = AsteroidMap(input)
        Assert.assertEquals(35, map.bestMonitoringStation())
    }

    @Test
    fun `example 4`() {
        val input = ".#..#..###\n" +
                "####.###.#\n" +
                "....###.#.\n" +
                "..###.##.#\n" +
                "##.##.#.#.\n" +
                "....###..#\n" +
                "..#.#..#.#\n" +
                "#..#.#.###\n" +
                ".##...##.#\n" +
                ".....#.#.."
        val map = AsteroidMap(input)
        Assert.assertEquals(41, map.bestMonitoringStation())
    }

    @Test
    fun `example 5`() {
        val input = ".#..##.###...#######\n" +
                "##.############..##.\n" +
                ".#.######.########.#\n" +
                ".###.#######.####.#.\n" +
                "#####.##.#.##.###.##\n" +
                "..#####..#.#########\n" +
                "####################\n" +
                "#.####....###.#.#.##\n" +
                "##.#################\n" +
                "#####.##.###..####..\n" +
                "..######..##.#######\n" +
                "####.##.####...##..#\n" +
                ".#####..#.######.###\n" +
                "##...#.##########...\n" +
                "#.##########.#######\n" +
                ".####.#.###.###.#.##\n" +
                "....##.##.###..#####\n" +
                ".#.#.###########.###\n" +
                "#.#.#.#####.####.###\n" +
                "###.##.####.##.#..##"
        val map = AsteroidMap(input)
        Assert.assertEquals(210, map.bestMonitoringStation())
    }


}