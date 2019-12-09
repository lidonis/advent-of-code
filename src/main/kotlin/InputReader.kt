class InputReader(fileName: String) {

    private val lines = this::class.java.getResourceAsStream(fileName).bufferedReader().readLines()

    fun asLinesOfLongs() = lines.map { line -> line.split(",").map { it.toLong() } }
    fun asLinesOfStrings() = lines.map { line -> line.split(",") }
    fun asIntRange() : IntRange {
        val values = lines[0].split("-").map { it.toInt() }
        return values[0]..values[1]
    }
}