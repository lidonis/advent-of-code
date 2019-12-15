import kotlin.math.max
import kotlin.math.min

fun main() {
    val inputs = InputReader("day15.txt").asLinesOfLongs()
    val drone = Drone(inputs[0])
    println(drone.explore())
}

class Drone(program: List<Long>) {
    private val computer = IntCodeComputer(program)
    private var direction = Direction.U
    private var position = Position(0, 0)
    private val shipMap = ShipMap()

    fun explore(): Position {
        loop@ while (true) {
            computer.input(direction.ordinal + 1L)
            //println("input $direction $status $position")
            when (computer.nextOutput()) {
                0L -> {
                    shipMap.add(position.move(direction))
                    //println("wall")
                }
                1L -> {
                    position = position.move(direction)
                    //println("move")
                }
                2L -> break@loop
            }
            direction++

        }
        shipMap.display(position)
        return position
    }
}

class ShipMap {
    private var xMin = 0
    private var yMin = 0
    private var xMax = 0
    private var yMax = 0
    private val walls = mutableListOf<Position>()

    fun add(move: Position) {
        resizeMap(move)
        walls.add(move)
    }

    private fun resizeMap(move: Position) {
        xMin = min(xMin, move.x)
        yMin = min(yMin, move.y)
        xMax = max(xMax, move.x)
        yMax = max(yMax, move.y)
    }

    fun display(drone: Position) {
        //println(walls)
        resizeMap(drone)
        for (j in yMax downTo yMin) {
            for (i in xMin..xMax) {
                val position = Position(i, j)
                when {
                    walls.contains(position) -> print('#')
                    i == 0 && j == 0 -> print('S')
                    position == drone -> print('D')
                    else -> print(' ')
                }
            }
            print("\n")
        }
    }

    override fun toString(): String {
        return "ShipMap(xMin=$xMin, yMin=$yMin, xMax=$xMax, yMax=$yMax, walls=$walls)"
    }


}