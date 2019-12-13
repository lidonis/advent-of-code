import kotlin.math.absoluteValue

fun main() {
    val inputs = InputReader("day3.txt").asLinesOfStrings()
    val day3 = Grid(
        inputs[0],
        inputs[1]
    )
    val minDistance = day3.minDistance()
    println("Manhattan distance from the central port to the closest intersection: $minDistance")
    val steps = day3.steps()
    print("Fewest combined steps the wires must take to reach an intersection: $steps")
}

enum class Direction {
    R, D, L, U;

    operator fun inc() = values()[Math.floorMod(this.ordinal + 1, values().size - 1)]
    operator fun dec() = values()[Math.floorMod(this.ordinal - 1, values().size - 1)]
}


class Grid(input1: List<String>, input2: List<String>) {
    private val startingPosition = Position(1, 1)

    private var wire1 = Wire(startingPosition, input1)
    private var wire2 = Wire(startingPosition, input2)

    constructor(input1: String, input2: String) : this(input1.split(","), input2.split(","))

    fun minDistance() = wire1.drop(1).intersect(wire2.drop(1)).map { startingPosition.manhattanDistance(it) }.min()

    fun steps(): Int? {
        return wire1.drop(1).intersect(wire2.drop(1)).map {
            wire1.indexOf(it) + wire2.indexOf(it)
        }.min()
    }
}

class Wire(private val positions: List<Position>) : List<Position> by positions {

    constructor(
        startingPosition: Position,
        moves: List<String>
    ) : this(moves.fold(listOf(startingPosition)) { acc, move ->
        acc + Move.read(move).moves(acc.last())
    })

}

data class Move(val direction: Direction, val size: Int) {

    companion object MoveInterpreter {
        fun read(input: String): Move {
            return Move(Direction.valueOf(input[0].toString()), input.substring(1).toInt())
        }
    }

    fun moves(startingPosition: Position) =
        (0 until size).fold(listOf(startingPosition), { acc, _ -> acc + acc.last().move(direction) }).drop(1)
}

data class Position(val x: Int, val y: Int) {
    fun move(direction: Direction) =
        when (direction) {
            Direction.R -> Position(x + 1, y)
            Direction.D -> Position(x, y - 1)
            Direction.L -> Position(x - 1, y)
            Direction.U -> Position(x, y + 1)
        }

    fun manhattanDistance(other: Position) = (x - other.x).absoluteValue + (y - other.y).absoluteValue
}
