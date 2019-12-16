import kotlin.math.abs

fun main() {
    val input = InputReader("day16.txt").asLineOfLong()
    println(FlawedFrequencyTransmission.phases(input, 100).take(8).joinToString(""))
}

object FlawedFrequencyTransmission {
    private val basePattern = listOf(1, 0, -1, 0)

    fun phases(input: List<Long>, times: Int): List<Long> {
        var output = input
        repeat(times) {
            output = phase(output)
        }
        return output
    }

    fun phase(input: List<Long>) =
        input.mapIndexed { i, _ ->
            abs(input.drop(i).zip(repeatingPattern(i).take(input.size).toList()).map {
                it.first * it.second
            }.sum()) % 10
        }

    private fun repeatingPattern(i: Int) = sequence {
        while (true) {
            yieldAll(pattern(i))
        }
    }

    private fun pattern(i: Int) =
        basePattern.map { generateSequence { it }.take(i + 1).toList() }
            .flatten()

}