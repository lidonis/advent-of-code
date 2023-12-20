package fr.lidonis.adventofcode.y2022.day13

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonPrimitive

class DistressSignal(private val lines: List<String>) {
    private fun packets() = lines
        .filter(String::isNotBlank)
        .map { Json.parseToJsonElement(it) }

    fun countOrdered() = packets()
        .chunked(2)
        .mapIndexed { i, chunk ->
            if (chunk[0] <= chunk[1]) {
                i + 1
            } else {
                0
            }
        }.sum()

    fun decoderKey(): Int {
        val divider1 = Json.parseToJsonElement("[[2]]")
        val divider2 = Json.parseToJsonElement("[[6]]")
        val allPackets = buildList {
            addAll(packets())
            add(divider1)
            add(divider2)
        }.sortedWith { a, b -> a.compareTo(b) }
        val indexDiv1 = allPackets.indexOf(divider1) + 1
        val indexDiv2 = allPackets.indexOf(divider2) + 1
        return indexDiv1 * indexDiv2
    }
}

private operator fun JsonElement.compareTo(other: JsonElement): Int = when {
    this is JsonArray && other is JsonArray -> {
        val pair = zip(other).firstOrNull { it.first.compareTo(it.second) != 0 }
        pair?.first?.compareTo(pair.second) ?: size.compareTo(other.size)
    }

    this is JsonArray && other is JsonPrimitive -> {
        compareTo(JsonArray(listOf(other)))
    }
    this is JsonPrimitive && other is JsonArray -> {
        JsonArray(listOf(this)).compareTo(other)
    }
    else -> {
        jsonPrimitive.int.compareTo(other.jsonPrimitive.int)
    }
}
