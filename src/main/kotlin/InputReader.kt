class InputReader(private val fileName: String) {
    private fun reader() = this::class.java.getResourceAsStream(fileName).bufferedReader()
    fun lines(): List<String> = reader().readLines()
    fun text(): String = reader().readText()
}
