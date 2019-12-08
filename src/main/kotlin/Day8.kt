fun main() {
    val inputs = InputReader("day8.txt").asLinesOfStrings()
    val decoder = ImageDecoder(inputs[0][0], 25, 6)
    println(decoder.checksum())
    decoder.decode().map {
        it.map { c ->
            if (c == '0') {
                '_'
            } else {
                c
            } }}
            .forEach { println(it) }
        }

        class ImageDecoder(private val input: String, private val width: Int, private val height: Int) {

            private val layers = input.chunked(width * height)

            fun checksum(): Int {
                val minZeroLayer = layers.withIndex().minBy { (_, f) -> f.count { c -> c == '0' } }!!.index
                val min1 = layers[minZeroLayer].count { c -> c == '1' }
                val min2 = layers[minZeroLayer].count { c -> c == '2' }
                return min1 * min2
            }

            fun decode(): List<String> {
                return (0 until width * height).map { decode(it) }.chunked(width).map { it.joinToString("") }
            }

            private fun decode(index: Int, layer: Int = 0): Char {
                val pixel = layers[layer][index]
                return if (pixel == '0' || pixel == '1') {
                    pixel
                } else {
                    decode(index, layer + 1)
                }
            }

        }