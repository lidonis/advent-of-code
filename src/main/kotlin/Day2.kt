fun main() {
    val computer = IntCodeComputer(
        InputReader("day2.txt").asLinesOfLongs()[0]
    )
    computer.input(12, 2)
    println(computer.asSequence().last()[0])
    computer.reset()
    val inputs = findInputs(computer)
    println(100 * inputs.first + inputs.second)
}

fun findInputs(computer: IntCodeComputer): Pair<Long, Long> {
    for (noun in 0..99L) {
        for (verb in 0..99L) {
            computer.input(noun, verb)
            if (computer.asSequence().last()[0] == 19690720L) {
                return Pair(noun, verb)
            }
            computer.reset()
        }
    }
    throw IllegalStateException("No inputs found")
}

