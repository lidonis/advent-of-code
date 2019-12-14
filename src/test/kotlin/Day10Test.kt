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
        Assert.assertEquals(8, map.station()?.second)
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
        Assert.assertEquals(33, map.station()?.second)
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
        Assert.assertEquals(35, map.station()?.second)
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
        Assert.assertEquals(41, map.station()?.second)
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
        Assert.assertEquals(210, map.station()?.second)
    }


    @Test
    fun `part 1`() {
        val input = InputReader("day10.txt").text
        val map = AsteroidMap(input)
        Assert.assertEquals(274, map.station()?.second)
    }

    @Test
    fun `part 2`() {
        val input = InputReader("day10.txt").text
        val map = AsteroidMap(input)
        Assert.assertEquals(Coordinate(3,5), map.shootingSequence().elementAt(199))
    }

    @Test
    fun `correct shoot looping`() {
        val input = InputReader("day10.txt").text
        val map = AsteroidMap(input)
        Assert.assertEquals(Coordinate(1,14), map.shootingSequence().last())
    }
}