package fr.lidonis.adventofcode.y2019.day14

import fr.lidonis.adventofcode.common.head
import kotlin.math.ceil

class NanoFactory(input: String) {

    private val reactions =
        input
            .lines()
            .map { it.split(" => ") }
            .map { line ->
                Reaction(line[0].split(", ").map { Chemical(it) }, Chemical(line[1]))
            }

    fun minimumOre(): Int {
        var currentChemicals = listOf(
            Chemical(
                1,
                "FUEL"
            )
        )
        while (isFinished(currentChemicals)) {
            val react = currentChemicals.flatMap { product ->
                val reaction = findReaction(product.name)
                reaction.reagents.flatMap {
                    if (it.leftOver) {
                        emptyList()
                    } else {
                        listOf(
                            Chemical(
                                (ceil((product.quantity.toDouble() / reaction.product.quantity)) * it.quantity).toInt(),
                                it.name
                            ),
                            Chemical(
                                product.quantity - (product.quantity % reaction.product.quantity),
                                product.name,
                                leftOver = true
                            )
                        )
                    }
                }
            }
            currentChemicals = react.fold(mutableListOf()) { acc, c ->
                val index = acc.indexOfFirst { it.name == c.name }
                if (index != -1) {
                    acc[index] = Chemical(
                        acc[index].quantity + c.quantity,
                        c.name
                    )
                } else if (c.quantity > 0) {
                    acc.add(c)
                }
                acc
            }
        }
        return currentChemicals.head.quantity
    }

    private fun isFinished(currentChemicals: List<Chemical>) = !currentChemicals.all { it.name == "ORE" || it.leftOver }

    private fun findReaction(name: String) =
        reactions.firstOrNull { it.product.name == name } ?: Reaction(
            listOf(Chemical(1, name)), (Chemical(1, name))
        )

    data class Reaction(val reagents: List<Chemical>, val product: Chemical)

    data class Chemical(val quantity: Int, val name: String, val leftOver: Boolean = false) {
        constructor(input: String) : this(input.split(" ")[0].toInt(), input.split(" ")[1])
    }
}
