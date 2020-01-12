package fr.lidonis.adventofcode.y2019.day8

class ImageDecoder(input: String, private val width: Int, private val height: Int) {

    private val layers = input.chunked(width * height)

    fun checksum() = minZeroLayer()
        .run { count { it == '1' } * count { it == '2' } }

    private fun minZeroLayer() = layers.minBy { f -> f.count { it == '0' } } ?: error { "Couldn't find min zero layer" }

    fun decode() = (0 until width * height).map { decode(it) }.chunked(width).map { it.joinToString("") }

    private fun decode(index: Int, layer: Int = 0): Char = layers[layer][index].run {
        when (this) {
            '2' -> decode(index, layer + 1)
            '0', '1' -> this
            else -> error { "Invalid image" }
        }
    }

}