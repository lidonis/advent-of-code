import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputer

fun main() {
    val program = InputReader("day13.txt").asLineOfLongs()
    val arcadeCabinet = ArcadeCabinet(program)
    //println(arcadeCabinet.countBlock())
    println(arcadeCabinet.play())
}


class ArcadeCabinet(program: List<Long>) {
    private val intCodeComputer =
        IntCodeComputer(program)

    fun countBlock(): Int {
        intCodeComputer.asSequence().last();
        return intCodeComputer.outputs.filterIndexed { i, v -> (i % 3 == 2) && (v == 2L) }.count()
    }

    fun play(): Int {
        intCodeComputer.asSequence().last();
        return 0
    }
}