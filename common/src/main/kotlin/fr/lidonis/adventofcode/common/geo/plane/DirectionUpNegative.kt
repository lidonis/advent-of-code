package fr.lidonis.adventofcode.common.geo.plane

enum class DirectionUpNegative(val symbol: Char, val move: Position) {
    UP('^', Position(0, -1)),
    RIGHT('>', Position(1, 0)),
    DOWN('v', Position(0, 1)),
    LEFT('<', Position(-1, 0));

    operator fun plus(value: Int) = entries[Math.floorMod(ordinal + value, Direction.entries.size)]
    operator fun minus(i: Int) = this + (-i)
    operator fun inc() = turnRight()
    operator fun dec() = turnLeft()

    fun turnRight() = this + 1
    fun turnLeft() = this - 1

    companion object {
        private val symbols by lazy { entries.associateBy { it.symbol } }

        fun fromSymbol(c: Char) = symbols[c]
    }
}
