import kotlin.math.abs
import kotlin.math.pow

fun main() {
    val input = InputReader("day16.txt")
    val fft = FlawedFrequencyTransmission(input.asLineOfInt())
    println(fft.phases())
    println(fft.embedded())
}


class FlawedFrequencyTransmission(private val input: List<Int>) {
    private val basePattern = listOf(1, 0, -1, 0)

    fun phases(times: Int = 100): String {
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
            yieldAll(basePattern.flatMap { generateSequence { it }.take(i + 1).toList() })
        }
    }

    fun embedded(times: Int = 100): String {
        val offset = input.take(7).reversed().mapIndexed { i, v -> 10.0.pow(i) * v }.sum().toInt()
        val value = (offset until 10000 * input.size).map { input[it % input.size] }.toIntArray()
        require(offset < 10000 * input.size) { "Offset out of bound " }
        println(" ${10000 * input.size} <= ${2 * offset}")
        require(10000 * input.size <= 2 * offset) { "This code optimisation only works in that case" }
        repeat(times) {
            value.indices.reversed().fold(0) { acc, i ->
                (abs(acc + value[i]) % 10).also { value[i] = it }
            }
        }
        return value.take(8).joinToString("")
    }

}