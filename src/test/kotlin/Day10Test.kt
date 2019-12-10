import AsteroidMap.*
import org.junit.Assert
import org.junit.Test

internal class Day10Test {

    @Test
    fun `test straight line`() {
        val map = AsteroidMap(listOf(Coordinate(0,0), Coordinate(1, 0), Coordinate(2, 0)))
        Assert.assertEquals(listOf(Coordinate(0,0), Coordinate(1, 0)), map.monitor(Coordinate( 0, 0)))
    }

    @Test
    fun `test diagonal line`() {
        val map = AsteroidMap(listOf(Coordinate(0,0), Coordinate(1, 3), Coordinate(2, 6)))
        Assert.assertEquals(listOf(Coordinate(0,0), Coordinate(1, 3)), map.monitor(Coordinate( 0, 0)))
    }

    @Test
    fun `test line`() {
        val map = AsteroidMap(listOf(Coordinate(0,0), Coordinate(2, 3   ), Coordinate(3, 3),  Coordinate(3, 4)))
        Assert.assertEquals(listOf(Coordinate(0,0), Coordinate(2, 3), Coordinate(3, 3)), map.monitor(Coordinate( 0, 0)))
    }

    @Test
    fun bug() {
        val input = ".#..#\n" +
                ".....\n" +
                "#####\n" +
                "....#\n" +
                "...##"
        val map = AsteroidMap(input)
        Assert.assertEquals(listOf(Coordinate(0,0), Coordinate(2, 3), Coordinate(3, 3)), map.monitor(Coordinate( 2, 0)))
    }

    @Test
    fun `example 1`() {
        val input = ".#..#\n" +
                ".....\n" +
                "#####\n" +
                "....#\n" +
                "...##"
        val map = AsteroidMap(input)
        println(map.monitorings())
        Assert.assertEquals(Coordinate( 2, 3) to 8, map.bestMonitoringStation())
    }

    @Test
    fun `example 2`() {
        val input = "......#.#.\n" +
                " #..#.#....\n" +
                "..#######.\n" +
                ".#.#.###..\n" +
                " .#..#.....\n" +
                " ..#....#.#\n" +
                "#..#....#.\n" +
                ".##.#..###\n" +
                "##...#..#.\n" +
                ".#....####"
        val map = AsteroidMap(input)
        Assert.assertEquals(Coordinate( 5, 8) to 33, map.bestMonitoringStation())
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
        Assert.assertEquals(Coordinate( 1, 2) to 35, map.bestMonitoringStation())
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
        Assert.assertEquals(Coordinate( 6, 3) to 41, map.bestMonitoringStation())
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
        Assert.assertEquals(Coordinate( 11, 13) to 210, map.bestMonitoringStation())
    }


}