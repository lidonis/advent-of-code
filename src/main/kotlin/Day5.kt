fun main() {
    val inputs = InputReader("day5.txt").asLinesOfLongs()
    val computer2 = IntCodeComputer(inputs[0])
    computer2.input(5)
    println(computer2.nextOutput())
}
