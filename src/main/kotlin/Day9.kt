fun main() {
    val program = InputReader("day9.txt").asLinesOfLongs()[0]
    val computer = IntCodeComputer(program)
    computer.input(1)
    computer.asSequence().last()
    println(computer.outputs)
    computer.reset()
    computer.input(2)
    computer.asSequence().last()
    println(computer.outputs)
}