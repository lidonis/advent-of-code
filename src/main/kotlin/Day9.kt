fun main() {
    val program = InputReader("day9.txt").asLinesOfLongs()[0]
    val computer =  IntCodeComputer(program, mutableListOf(1))
    computer.asSequence().last()
    println(computer.outputs)
    computer.inputs =  mutableListOf(2)
    computer.reset()
    computer.asSequence().last()
    println(computer.outputs)
}