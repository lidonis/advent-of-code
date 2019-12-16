import kotlin.math.abs

fun main() {
    val times = 100
    val input = InputReader("day16.txt")
    println(FlawedFrequencyTransmission.embedded(input.asLineOfInt(), times))
}


object FlawedFrequencyTransmission {
    private val basePattern = listOf(1, 0, -1, 0)

    fun phases(input: List<Int>, times: Int): String {
        var output = input
        repeat(times) {
            output = phase(output)
        }
        return output.take(8).joinToString("")
    }

    fun phase(input: List<Int>) =
        input.mapIndexed { i, _ ->
            abs(input.drop(i).zip(repeatingPattern(i).take(input.size).toList()).map {
                it.first * it.second
            }.sum()) % 10
        }

    private fun repeatingPattern(i: Int) = sequence {
        while (true) {
            yieldAll(basePattern.map { generateSequence { it }.take(i + 1).toList() }
                .flatten())
        }
    }

    fun embedded(input: List<Int>, times: Int): String {
        val offset = input.take(7).fold(0) { acc, digit -> 10 * acc + digit }
        val value = (offset until 10000 * input.size).map { input[it % input.size] }.toIntArray()
        repeat(times) {
            value.indices.reversed().fold(0) { acc, i ->
                (abs(acc + value[i]) % 10).also { value[i] = it }
            }
        }
        return value.take(8).joinToString("")
    }

}