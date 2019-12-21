fun main() {
    val input = InputReader("day18.txt").text
    val vault = Vault(input)
    println(vault.shortestPathStepCount())
}

class Vault(input: String) {

    private val vaultMap = mutableMapOf<Position, Tile>()
    private val keys = mutableSetOf<Key>()
    private val entrances = mutableSetOf<Position>()

    init {
        input
            .split("\n")
            .forEachIndexed { j, s ->
                s.forEachIndexed { i, c ->
                    val tile = TileFactory.from(c)
                    val position = Position(i, j)
                    vaultMap[position] = tile
                    when (tile) {
                        is Key -> keys.add(tile)
                        is Entrance -> entrances.add(position)
                    }
                }
            }
    }

    fun shortestPathStepCount(): Int {
        val start = entrances.first()
        var steps = 1
        val nodeStart = Node(start)
        val visited = mutableSetOf(nodeStart)
        var neighbours = getNeighbours(nodeStart, visited)
        while (true) {
            if (neighbours.any { it.keys.size == keys.size }) {
                break
            }
            visited.addAll(neighbours)
            steps++
            println("$steps ${visited.size}")
            neighbours = neighbours.map { getNeighbours(it, visited) }.flatten().toSet()
        }
        return steps
    }

    private fun getNeighbours(node: Node, visited: Set<Node>): Set<Node> {
        return node.position.neighbours()
            .filter { vaultMap[it]?.access(node.keys) ?: false }
            .mapNotNull {
                val keys = node.keys.toMutableSet()
                val tile = vaultMap[it]!!
                if (tile is Key)
                    keys += tile
                val neighbour = Node(it, keys)
                if (neighbour in visited) {
                    null
                } else {
                    neighbour
                }
            }.toSet()
    }
}

data class Node(val position: Position, val keys: Set<Key> = emptySet())

sealed class Tile {
    abstract fun access(keys: Set<Key>): Boolean
}

object OpenPassage : Tile() {
    override fun access(keys: Set<Key>) = true
}

object StoneWall : Tile() {
    override fun access(keys: Set<Key>) = false
}

object Entrance : Tile() {
    override fun access(keys: Set<Key>) = true
}

data class Key(private val letter: Char) : Tile() {
    override fun access(keys: Set<Key>) = true
}

data class Door(private val letter: Char) : Tile() {
    private val key = Key(letter.toLowerCase())
    override fun access(keys: Set<Key>) = key in keys
}

object TileFactory {
    fun from(c: Char) =
        when (c) {
            '.' -> OpenPassage
            '#' -> StoneWall
            '@' -> Entrance
            in 'a'..'z' -> Key(c)
            in 'A'..'Z' -> Door(c)
            else -> throw IllegalStateException("")
        }
}