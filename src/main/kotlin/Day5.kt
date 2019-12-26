import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputer

fun main() {
    val input = InputReader("day5.txt").asLineOfLongs()
    val computer2 = IntCodeComputer(input)
    computer2.input(5)
    println(computer2.nextOutput())
}
