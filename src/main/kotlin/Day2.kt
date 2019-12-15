fun main() {
    val computer = IntCodeComputer(
        InputReader("day2.txt").asLinesOfLongs()[0]
    )
    computer[1] = 12
    computer[2] = 2
    println(computer.asSequence().last().memory[0])
    computer.reset()
    val inputs = findInputs(computer)
    println(100 * inputs.first + inputs.second)
}

fun findInputs(computer: IntCodeComputer): Pair<Long, Long> {
    for (noun in 0..99L) {
        for (verb in 0..99L) {
            computer[1] = noun
            computer[2] = verb
            if (computer.asSequence().last().memory[0] == 19690720L) {
                return noun to verb
            }
            computer.reset()
        }
    }
    throw IllegalStateException("No inputs found")
}

