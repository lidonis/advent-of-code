fun main() {
    val inputs = InputReader("day5.txt").asLinesOfLongs()
    val computer2 = IntCodeComputer(
        inputs[0] , mutableListOf(5)
    )
    computer2.asSequence().last()
    println(computer2.outputs.last())

}
