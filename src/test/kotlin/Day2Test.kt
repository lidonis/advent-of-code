import org.junit.Assert
import org.junit.Test

internal class Day2Test {

    @Test
    fun program1() {
        val computer = IntCodeComputer(listOf(1, 0, 0, 0, 99))
        Assert.assertEquals(listOf(2, 0, 0, 0, 99), computer.next())
        Assert.assertFalse(computer.hasNext())
    }

    @Test
    fun program2() {
        val computer = IntCodeComputer(listOf(2, 3, 0, 3, 99))
        Assert.assertEquals(listOf(2, 3, 0, 6, 99), computer.next())
        Assert.assertFalse(computer.hasNext())
    }

    @Test
    fun program3() {
        val computer = IntCodeComputer(listOf(2, 4, 4, 5, 99, 0))
        Assert.assertEquals(listOf(2, 4, 4, 5, 99, 9801), computer.next())
        Assert.assertFalse(computer.hasNext())
    }

    @Test
    fun program4() {
        val computer = IntCodeComputer(listOf(1, 1, 1, 4, 99, 5, 6, 0, 99))
        Assert.assertEquals(listOf(30, 1, 1, 4, 2, 5, 6, 0, 99), computer.asSequence().last())
        Assert.assertFalse(computer.hasNext())
    }

    @Test
    fun program5() {
        val computer = IntCodeComputer(listOf(1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50))
        Assert.assertEquals(
            listOf(
                1, 9, 10, 70,
                2, 3, 11, 0,
                99,
                30, 40, 50
            ), computer.next()
        )
        Assert.assertEquals(
            listOf(
                3500, 9, 10, 70,
                2, 3, 11, 0,
                99,
                30, 40, 50
            ), computer.next()
        )
        Assert.assertFalse(computer.hasNext())
    }
}