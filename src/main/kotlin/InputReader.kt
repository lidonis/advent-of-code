class InputReader(fileName: String) {
    private val reader = this::class.java.getResourceAsStream(fileName).bufferedReader()
    val lines: List<String>
        get() = reader.readLines()
    val text: String
        get() = reader.readText()

    fun asLineOfLongs() = asLinesOfLongs()[0]
    fun asLinesOfLongs() = lines.map { line -> line.split(",").map { it.toLong() } }
    fun asLinesOfStrings() = lines.map { line -> line.split(",") }
    fun asLineOfInt() = lines[0].map { Character.digit(it, 10) }
    fun asIntRange(): IntRange {
        val values = lines[0].split("-").map { it.toInt() }
        return values[0]..values[1]
    }
}