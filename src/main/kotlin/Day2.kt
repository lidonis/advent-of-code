fun main() {
    val computer = IntCodeComputer(
        InputReader("day2.txt").asLinesOfInts()[0]
    )
    computer.input(12, 2)
    println(computer.asSequence().last()[0])
    computer.reset()
    val inputs = findInputs(computer)
    println(100 * inputs.first + inputs.second)
}

fun findInputs(computer: IntCodeComputer): Pair<Int, Int> {
    for (noun in 0..99) {
        for (verb in 0..99) {
            computer.input(noun, verb)
            if (computer.asSequence().last()[0] == 19690720) {
                return Pair(noun, verb)
            }
            computer.reset()
        }
    }
    throw IllegalStateException("No inputs found")
}

