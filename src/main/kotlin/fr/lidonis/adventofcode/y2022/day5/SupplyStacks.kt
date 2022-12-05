package fr.lidonis.adventofcode.y2022.day5

private val MOVE_PATTERN = """move (\d+) from (\d) to (\d)""".toRegex()

class SupplyStacks(private val lines: List<String>) {
    private val initialStacks: Map<Int, String>
    private val moves: List<Move>

    init {
        val n = lines.indexOf("")
        initialStacks = buildMap {
            lines[n - 1].forEachIndexed { i, c ->
                if (!c.isDigit()) return@forEachIndexed
                for (j in 0 until n) {
                    if (lines[j].getOrNull(i)?.isWhitespace() == false) {
                        put(c.digitToInt(), buildString(n - 1 - j) { for (k in j until n - 1) append(lines[k][i]) })
                        break
                    }
                }
            }
        }
        moves = buildList(lines.size - n - 1) {
            for (i in n + 1 until lines.size) {
                val (quantity, x, y) = MOVE_PATTERN.matchEntire(lines[i])!!.destructured
                add(Move(quantity.toInt(), x.toInt(), y.toInt()))
            }
        }
    }

    fun move(): String {
        val stacks = initialStacks.toMutableMap()
        for (move in moves) {
            val source = stacks.getValue(move.from)
            stacks[move.from] = source.drop(move.quantity)
            val moving = source.take(move.quantity).reversed()
            stacks[move.to] = moving + stacks.getValue(move.to)
        }
        return stacks.values.joinToString("") { it.first().toString() }
    }

    fun moveBulk(): String {
        val stacks = initialStacks.toMutableMap()
        for (move in moves) {
            val source = stacks.getValue(move.from)
            stacks[move.from] = source.drop(move.quantity)
            val moving = source.take(move.quantity)
            stacks[move.to] = moving + stacks.getValue(move.to)
        }
        return stacks.values.joinToString("") { it.first().toString() }
    }

    private data class Move(val quantity: Int, val from: Int, val to: Int)
}

