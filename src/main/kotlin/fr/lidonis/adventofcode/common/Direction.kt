package fr.lidonis.adventofcode.common

enum class Direction(val letter: Char) {
    UP('U'), RIGHT('R'), DOWN('D'), LEFT('L');

    operator fun inc() = turnRight()
    operator fun dec() = turnLeft()

    fun turnRight() = values()[Math.floorMod(this.ordinal + 1, values().size)]
    fun turnLeft() = values()[Math.floorMod(this.ordinal - 1, values().size)]

    companion object {
        private val letters by lazy { values().map { it.letter to it }.toMap() }

        fun fromChar(c: Char) = letters[c]
    }
}