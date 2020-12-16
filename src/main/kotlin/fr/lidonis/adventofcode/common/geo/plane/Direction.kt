package fr.lidonis.adventofcode.common.geo.plane

enum class Direction(val letter: Char, val move: Position) {
    UP('U', Position(0, 1)),
    RIGHT('R', Position(1, 0)),
    DOWN('D', Position(0, -1)),
    LEFT('L', Position(-1, 0));

    operator fun plus(value: Int) = values()[Math.floorMod(ordinal + value, values().size)]
    operator fun minus(i: Int) = this + (-i)
    operator fun inc() = turnRight()
    operator fun dec() = turnLeft()

    fun turnRight() = this + 1
    fun turnLeft() = this - 1

    companion object {
        private val letters by lazy { values().map { it.letter to it }.toMap() }
        private val positions by lazy { values().map { it.move to it }.toMap() }

        fun fromChar(c: Char) = letters[c]

        fun fromPosition(position: Position) = positions[position]
    }
}
