import kotlin.streams.asSequence
import kotlin.streams.toList

class InputReader(fileName: String) {
    private val reader = this::class.java.getResourceAsStream(fileName).bufferedReader()
    val lines: List<String>
        get() = reader.readLines()
    val text: String
        get() = reader.readText()

    fun asLinesOfInt() = lines.map { it.toInt() }
    fun asLinesOfLongs() = lines.map { line -> line.split(",").map { it.toLong() } }
    fun asLinesOfStrings() = lines.map { line -> line.split(",") }
    fun asLineOfLong() = lines[0].chars().mapToLong() { it.toLong() }.toList()
    fun asIntRange(): IntRange {
        val values = lines[0].split("-").map { it.toInt() }
        return values[0]..values[1]
    }
}