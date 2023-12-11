package fr.lidonis.adventofcode.common.geo.plane

enum class Direction(val letter: Char, val move: Position) {
    UP('U', Position(0, 1)),
    RIGHT('R', Position(1, 0)),
    DOWN('D', Position(0, -1)),
    LEFT('L', Position(-1, 0));

    operator fun plus(value: Int) = entries[Math.floorMod(ordinal + value, entries.size)]
    operator fun minus(i: Int) = this + (-i)
    operator fun inc() = turnRight()
    operator fun dec() = turnLeft()

    fun turnRight() = this + 1
    fun turnLeft() = this - 1

    companion object {
        private val letters by lazy { entries.associateBy { it.letter } }

        fun fromChar(c: Char) = letters[c]
    }
}
