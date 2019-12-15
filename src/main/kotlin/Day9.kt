fun main() {
    val program = InputReader("day9.txt").asLinesOfLongs()[0]
    val computer = IntCodeComputer(program)
    computer.input(1)
    println(computer.nextOutput())
    computer.reset()
    computer.input(2)
    println(computer.nextOutput())
}