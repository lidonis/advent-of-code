package fr.lidonis.adventofcode.common.geo.plane

enum class Direction(val letter: Char, val move: Position) {
    UP('U', Position(0, 1)),
    RIGHT('R', Position(1, 0)),
    DOWN('D', Position(0, -1)),
    LEFT('L', Position(-1, 0));

    operator fun inc() = turnRight()
    operator fun dec() = turnLeft()

    fun turnRight() = values()[Math.floorMod(this.ordinal + 1, values().size)]
    fun turnLeft() = values()[Math.floorMod(this.ordinal - 1, values().size)]

    companion object {
        private val letters by lazy { values().map { it.letter to it }.toMap() }

        fun fromChar(c: Char) = letters[c]
    }
}
