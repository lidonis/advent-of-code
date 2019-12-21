import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class Day10Test {

    @Test
    fun `example 1`() {
        val input = ".#..#\n" +
                ".....\n" +
                "#####\n" +
                "....#\n" +
                "...##"
        val map = AsteroidMap(input)
        Assertions.assertEquals(8, map.station()?.second)
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
        Assertions.assertEquals(33, map.station()?.second)
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
        Assertions.assertEquals(35, map.station()?.second)
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
        Assertions.assertEquals(41, map.station()?.second)
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
        Assertions.assertEquals(210, map.station()?.second)
    }


    @Test
    fun `part 1`() {
        val input = InputReader("day10.txt").text
        val map = AsteroidMap(input)
        Assertions.assertEquals(274, map.station()?.second)
    }

    @Test
    fun `part 2`() {
        val input = InputReader("day10.txt").text
        val map = AsteroidMap(input)
        Assertions.assertEquals(305, map.part2())
    }
}