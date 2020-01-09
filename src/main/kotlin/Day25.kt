import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputerFactory

fun main() {
    val computer = IntCodeComputerFactory.buildASCIIComputer(InputReader("day25.txt").text())
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