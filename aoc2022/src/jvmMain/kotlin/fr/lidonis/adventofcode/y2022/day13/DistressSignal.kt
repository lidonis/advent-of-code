package fr.lidonis.adventofcode.y2022.day13

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.IntNode
class DistressSignal(private val lines: List<String>) {

    private val mapper = ObjectMapper()

    private fun packets() = lines
        .filter(String::isNotBlank)
        .map { mapper.readTree(it) }

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
        val divider1 = mapper.readTree("[[2]]")
        val divider2 = mapper.readTree("[[6]]")
        val allPackets = mutableListOf<JsonNode>()
        allPackets.addAll(packets())
        allPackets.add(divider1)
        allPackets.add(divider2)
        allPackets.sortWith { a, b -> a.compareTo(b) }
        val indexDiv1 = allPackets.indexOf(divider1) + 1
        val indexDiv2 = allPackets.indexOf(divider2) + 1
        return indexDiv1 * indexDiv2
    }
}

private operator fun JsonNode.compareTo(other: JsonNode): Int = when {
    this is ArrayNode && other is ArrayNode -> {
        val pair = this.toList().zip(other.toList()).firstOrNull { it.first.compareTo(it.second) != 0 }
        pair?.first?.compareTo(pair.second) ?: this.size().compareTo(other.size())
    }
    this is ArrayNode && other is IntNode -> this.compareTo(ObjectMapper().createArrayNode().add(other))
    this is IntNode && other is ArrayNode -> ObjectMapper().createArrayNode().add(this).compareTo(other)
    this is IntNode && other is IntNode -> this.intValue().compareTo(other.intValue())
    else -> error("wrong type")
}
