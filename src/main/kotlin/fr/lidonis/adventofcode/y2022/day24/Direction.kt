package fr.lidonis.adventofcode.y2022.day24

import fr.lidonis.adventofcode.common.geo.plane.Position

enum class Direction(val symbol: Char, val move: Position) {
    UP('^', Position(0, -1)),
    RIGHT('>', Position(1, 0)),
    DOWN('v', Position(0, 1)),
    LEFT('<', Position(-1, 0));

    companion object {
        private val symbols by lazy { values().associateBy { it.symbol } }

        fun fromSymbol(c: Char) = symbols[c]
    }
}
