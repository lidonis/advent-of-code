import com.sun.jmx.remote.internal.ArrayQueue
import org.junit.Assert
import org.junit.Test
import java.util.*

internal class Day9Test {

    @Test
    fun `example 1`() {
        val program = listOf(109L, 1, 204, -1, 1001, 100, 1, 100, 1008, 100, 16, 101, 1006, 101, 0, 99)
        val computer = IntCodeComputer(program)
        computer.asSequence().last()
        Assert.assertEquals(program, computer.outputs.map { it })
    }

    @Test
    fun `example 2`() {
        val computer = IntCodeComputer(listOf(1102,34915192,34915192,7,4,7,99,0))
        computer.asSequence().last()
        Assert.assertEquals(34915192L * 34915192, computer.outputs.poll())
    }

    @Test
    fun `example 3`() {
        val computer = IntCodeComputer(listOf(104,1125899906842624,99))
        computer.asSequence().last()
        Assert.assertEquals(1125899906842624, computer.outputs.poll())
    }

    @Test
    fun `day 9`() {
        val program = InputReader("day9.txt").asLinesOfLongs()[0]
        val computer = IntCodeComputer(program)
        computer.input(1)
        computer.asSequence().last()
        Assert.assertEquals(3601950151, computer.outputs.poll())
        computer.reset()
        computer.input(2)
        computer.asSequence().last()
        Assert.assertEquals(64236, computer.outputs.poll())
    }
}