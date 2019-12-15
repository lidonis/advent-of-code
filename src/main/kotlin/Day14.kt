import kotlin.math.ceil

class NanoFactory(input: String) {

    private val reactions =
        input
            .split("\n")
            .map { it.split(" => ") }
            .map { it[0].split(", ").map { c -> Chemical(c) } to Chemical(it[1]) }


    fun minimumOre(): Int {
        var currentChemicals = listOf(Chemical(1, "FUEL"))
        while (isFinished(currentChemicals)) {
            val react = currentChemicals.map { product ->
                val reaction = findReaction(product.name)
                reaction.first.map {
                    if(it.leftOver){
                        emptyList()
                    }else {
                        listOf(
                            Chemical(
                                (ceil((product.quantity.toDouble() / reaction.second.quantity)) * it.quantity).toInt(),
                                it.name
                            ),
                            Chemical(
                                product.quantity - (product.quantity % reaction.second.quantity),
                                product.name,
                                leftOver = true
                            )
                        )
                    }
                }
            }
            currentChemicals = react.flatten().flatten().fold(mutableListOf()) { acc, c ->
                val index = acc.indexOfFirst { it.name == c.name }
                if (index != -1) {
                    acc[index] = Chemical(acc[index].quantity + c.quantity, c.name)
                } else if(c.quantity > 0){
                    acc.add(c)
                }
                acc
            }
            println(currentChemicals)
        }
        return currentChemicals[0].quantity
    }

    private fun isFinished(currentChemicals: List<Chemical>) = !currentChemicals.all { it.name == "ORE" || it.leftOver }

    private fun findReaction(name: String) = reactions.firstOrNull { it.second.name == name }
        ?: listOf(Chemical(1, name)) to (Chemical(1, name))


    data class Chemical(val quantity: Int, val name: String, val leftOver: Boolean = false) {
        constructor(input: String) : this(input.split(" ")[0].toInt(), input.split(" ")[1])
    }
}