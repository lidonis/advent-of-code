import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputerFactory

fun main() {
    val program = InputReader("day25.txt").asLineOfLongs()
    val computer = IntCodeComputerFactory.buildASCIIComputer(program)
    while (true) {
        computer.tryNextOutputChar(10)?.run {
            print(this)
            if (this == '?') {
                readLine()?.run {
                    computer.input(this)
                }
            }
        }
    }
}