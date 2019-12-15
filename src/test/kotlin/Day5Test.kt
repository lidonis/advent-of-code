import org.junit.Assert
import org.junit.Test

internal class Day5Test {

    @Test
    fun testInput() {
        val computer = IntCodeComputer(listOf(3,1))
        computer.input(10)
        Assert.assertEquals(listOf(3L,10), computer.next().memory.memory)
    }

    @Test
    fun `test output`() {
        val computer = IntCodeComputer(listOf(4,1))
        Assert.assertEquals(1L, computer.nextOutput())
    }

    @Test
    fun `test not equal 8 with position mode`() {
        val computer = IntCodeComputer(listOf(3,9,8,9,10,9,4,9,99,-1,8))
        computer.input(1)
        Assert.assertEquals(0L, computer.nextOutput())
    }

    @Test
    fun `test equals 8 with position mode`() {
        val computer = IntCodeComputer(listOf(3,9,8,9,10,9,4,9,99,-1,8))
        computer.input(8)
        Assert.assertEquals(1L, computer.nextOutput())
    }

    @Test
    fun `test less than 8 with position mode`() {
        val computer = IntCodeComputer(listOf(3,9,7,9,10,9,4,9,99,-1,8))
        computer.input(5)
        Assert.assertEquals(1L, computer.nextOutput())
    }

    @Test
    fun `test not less than 8 with position mode`() {
        val computer = IntCodeComputer(listOf(3,9,7,9,10,9,4,9,99,-1,8))
        computer.input(8)
        Assert.assertEquals(0L, computer.nextOutput())
    }


    @Test
    fun `test not equal 8 with  immediate mode`() {
        val computer = IntCodeComputer(listOf(3,3,1108,-1,8,3,4,3,99))
        computer.input(1)
        Assert.assertEquals(0L, computer.nextOutput())
    }

    @Test
    fun `test equals 8 with immediate mode`() {
        val computer = IntCodeComputer(listOf(3,3,1108,-1,8,3,4,3,99))
        computer.input(8)
        Assert.assertEquals(1L, computer.nextOutput())
    }

    @Test
    fun `test less than 8 with immediate mode`() {
        val computer = IntCodeComputer(listOf(3,3,1107,-1,8,3,4,3,99))
        computer.input(5)
        Assert.assertEquals(1L, computer.nextOutput())
    }

    @Test
    fun `test not less than 8 with immediate mode`() {
        val computer = IntCodeComputer(listOf(3,3,1107,-1,8,3,4,3,99))
        computer.input(8)
        Assert.assertEquals(0L, computer.nextOutput())
    }

    @Test
    fun `test jump with zero position mode`() {
        val computer = IntCodeComputer(listOf(3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9))
        computer.input(0)
        Assert.assertEquals(0L, computer.nextOutput())
    }

    @Test
    fun `test jump with non zero position mode`() {
        val computer = IntCodeComputer(listOf(3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9))
        computer.input(-6)
        Assert.assertEquals(1L, computer.nextOutput())
    }
}