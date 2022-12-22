package fr.lidonis.adventofcode.y2022.day17

import fr.lidonis.adventofcode.common.geo.plane.Direction
import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.common.geo.plane.PositionSet

private const val THREE = 3

private const val WIDTH = 7

class PyroclasticFlow(input: String) {

    private val eph = Eph(input)

    private val jets = input.map {
        when (it) {
            '<' -> Direction.LEFT
            '>' -> Direction.RIGHT
            else -> error("Not a direction")
        }
    }.asSequence().repeatForever()

    private val minusRock = PositionSet(
        setOf(
            Position(0, 0), Position(1, 0), Position(2, 0), Position(THREE, 0)
        )
    )

    private val plusRock = PositionSet(
        setOf(
            Position(1, 0),
            Position(0, 1), Position(1, 1), Position(2, 1),
            Position(1, 2)
        )
    )

    private val lRock = PositionSet(
        setOf(
            Position(0, 0),
            Position(1, 0),
            Position(2, 0),
            Position(2, 1),
            Position(2, 2),
        )
    )

    private val iRock = PositionSet(
        setOf(
            Position(0, 0),
            Position(0, 1),
            Position(0, 2),
            Position(0, 3),
        )
    )

    private val squareRock = PositionSet(
        setOf(
            Position(0, 0), Position(1, 0),
            Position(0, 1), Position(1, 1),
        )
    )

    private val rockTypes = listOf(minusRock, plusRock, lRock, iRock, squareRock)

    fun towerSize(i: Long): Long {
        return eph.solve(i)
        val rockSequence = rockTypes.asSequence().repeatForever().iterator()
        var rock = rockSequence.next().moveTo(Position(2, THREE))
        val jetsSequence = jets.iterator()
        var nbRock = 1
        val rocks = mutableSetOf<Position>()
        while (nbRock <= i) {
            val jet = jetsSequence.next()
            val rockPushed = rock.push(jet)
            if (rocks.intersect(rockPushed.positions).isEmpty()) {
                rock = rockPushed
            }
            val rockFall = rock.fall()
            if (rockFall.boundingBox.start.y < 0 || rocks.intersect(rockFall.positions).isNotEmpty()) {
                rocks.addAll(rock)
                val position = Position(2, PositionSet(rocks).boundingBox.end.y + THREE + 1)
                rock = rockSequence.next().moveTo(position)
                nbRock++
            } else {
                rock = rockFall
            }
        }

        return (PositionSet(rocks).boundingBox.end.y + 1).toLong()
    }

    private fun PositionSet.push(direction: Direction) = when (direction) {
        Direction.LEFT -> moveLeft()
        Direction.RIGHT -> moveRight()
        else -> error("Not a push")
    }

    private fun PositionSet.moveRight(): PositionSet {
        val right = moveBy(Direction.RIGHT.move)
        return if (right.boundingBox.end.x >= WIDTH) {
            this
        } else {
            right
        }
    }

    private fun PositionSet.moveLeft(): PositionSet {
        val left = moveBy(Direction.LEFT.move)
        return if (left.boundingBox.start.x < 0) {
            this
        } else {
            left
        }
    }

    private fun PositionSet.fall() = moveBy(Direction.DOWN.move)

    private fun <T> Sequence<T>.repeatForever() =
        generateSequence(this) { it }.flatten()
}

class Eph(private val jet: String) {
    fun solve(n: Long): Long {
        val buffer = mutableListOf<State>()
        val cycle = generateSequence(Triple(State(0, byteArrayOf(127)), 0, 0)) { (state, jetIndex, rockIndex) ->
            var i = jetIndex
            val rock = rocks[rockIndex]
            val maxX = 7 - rock.maxOf { it.length }
            var x = 2
            var y = state.height + 3
            while (true) {
                val x2 = when (jet[i]) {
                    '<' -> (x - 1).coerceAtLeast(0)
                    '>' -> (x + 1).coerceAtMost(maxX)
                    else -> throw IllegalArgumentException("${jet.take(i)}[${jet[i]}${jet.drop(i + 1)}")
                }
                i = (i + 1) % jet.length
                x = if (state.contains(x2, y, rock)) x else x2
                if (state.contains(x, y - 1, rock)) break
                y--
            }
            Triple(state.plus(x, y, rock), i, (rockIndex + 1) % rocks.size)
        }.onEach { buffer.add(it.first) }.map { (state, jetIndex, rockIndex) ->
            Triple(
                state.rows.asList(),
                jetIndex,
                rockIndex
            )
        }.take((n + 1).coerceAtMost(Int.MAX_VALUE.toLong()).toInt()).asIterable().findCycle()
        return if (cycle != null) {
            val height1 = buffer[cycle.first].height
            val height2 = buffer[cycle.second].height
            val dividend = n - cycle.first
            val divisor = cycle.second - cycle.first
            val quotient = dividend / divisor
            val remainder = dividend % divisor
            buffer[cycle.first + remainder.toIntExact()].height + (height2 - height1) * quotient
        } else {
            buffer[n.toIntExact()].height.toLong()
        }
    }

    private class State(height: Int, val rows: ByteArray) {
        private val yOffset = height - rows.size
        val height: Int get() = yOffset + rows.size

        fun contains(x: Int, y: Int, rock: Array<String>): Boolean {
            require(y >= yOffset) { "$yOffset/$y/$height" }
            rock.forEachIndexed { dy, line ->
                val row = rows.getOrElse(y - yOffset + dy) { return false }
                line.forEachIndexed { dx, c -> if (c == '#' && row.toInt() and 1.shl(x + dx) != 0) return true }
            }
            return false
        }

        @Suppress("CyclomaticComplexMethod")
        fun plus(x: Int, y: Int, rock: Array<String>): State {
            require(y >= yOffset)
            val newHeight = maxOf(height, y + rock.size)
            val rows = rows.copyOf(newHeight - yOffset)
            rock.forEachIndexed { dy, line ->
                var row = rows[y - yOffset + dy]
                line.forEachIndexed { dx, c -> if (c == '#') row = (row.toInt() or 1.shl(x + dx)).toByte() }
                rows[y - yOffset + dy] = row
            }
            val visible = ByteArray(rows.size + 1).apply { set(lastIndex, 1) }
            val queue = mutableListOf(visible.lastIndex shl 3)
            while (queue.isNotEmpty()) {
                val pos = queue.removeLast()
                if (pos < visible.lastIndex shl 3 && rows[pos shr 3].toInt() and 1.shl(pos and 7) != 0) continue
                if (pos > 7 && visible[pos - 8 shr 3].toInt() and 1.shl(pos and 7) == 0) {
                    visible[pos - 8 shr 3] = (visible[pos - 8 shr 3].toInt() or 1.shl(pos and 7)).toByte()
                    queue.add(pos - 8)
                }
                if (pos and 7 != 0 && visible[pos shr 3].toInt() and 1.shl(pos - 1 and 7) == 0) {
                    visible[pos shr 3] = (visible[pos shr 3].toInt() or 1.shl(pos - 1 and 7)).toByte()
                    queue.add(pos - 1)
                }
                if (pos and 7 < 6 && visible[pos shr 3].toInt() and 1.shl(pos + 1 and 7) == 0) {
                    visible[pos shr 3] = (visible[pos shr 3].toInt() or 1.shl(pos + 1 and 7)).toByte()
                    queue.add(pos + 1)
                }
                if (pos < visible.lastIndex shl 3 && visible[pos + 8 shr 3].toInt() and 1.shl(pos and 7) == 0) {
                    visible[pos + 8 shr 3] = (visible[pos + 8 shr 3].toInt() or 1.shl(pos and 7)).toByte()
                    queue.add(pos + 8)
                }
            }
            rows.forEachIndexed { i, row -> rows[i] = (row.toInt() and visible[i].toInt()).toByte() }
            val trim = rows.indexOfFirst { it != 0.toByte() }
            return State(newHeight, if (trim > 0) rows.copyOfRange(fromIndex = trim, toIndex = rows.size) else rows)
        }
    }
}

data class IntPair(val first: Int, val second: Int)

infix fun Int.to(other: Int): IntPair = IntPair(this, other)

private val rocks = arrayOf(
    arrayOf("####"),
    arrayOf(".#.", "###", ".#."),
    arrayOf("###", "..#", "..#"),
    arrayOf("#", "#", "#", "#"),
    arrayOf("##", "##")
)

private fun <T> Iterable<T>.findCycle(): IntPair? {
    val indices = mutableMapOf<T, Int>()
    forEachIndexed { j, value ->
        val i = indices.getOrPut(value) { j }
        if (i < j) return i to j
    }
    return null
}

private fun Long.toIntExact(): Int {
    require(this in Int.MIN_VALUE.toLong()..Int.MAX_VALUE.toLong())
    return this.toInt()
}
