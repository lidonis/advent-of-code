class InputReader(fileName: String) {
    private val reader = this::class.java.getResourceAsStream(fileName).bufferedReader()
    val lines: List<String>
        get() = reader.readLines()
    val text: String
        get() = reader.readText()

    fun asLineOfLongs() = text.split(",").map { it.toLong() }
    fun asLinesOfStrings() = lines.map { line -> line.split(",") }
    fun asLineOfInt() = text.map { Character.digit(it, 10) }
}