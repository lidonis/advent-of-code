package fr.lidonis.adventofcode.y2020.day24

data class Coordinate(val q: Int, val r: Int) {

    operator fun plus(move: Coordinate) = Coordinate(this.q + move.q, this.r + move.r)

    fun neighbours() = Direction.values().map { this + it.coordinate }

    companion object {
        fun fromDirections(directions: String) = directions.fold("" to Coordinate(0, 0)) { acc, c ->
            val current = acc.first + c
            Direction.fromInitials(current)?.let { "" to acc.second + it.coordinate }
                ?: if (current == "s" || current == "n") c.toString() to acc.second
                else error("wrong coordinate")
        }.second
    }

    enum class Direction(val initials: String, val coordinate: Coordinate) {
        EAST("e", Coordinate(1, 0)),
        SOUTH_EAST("se", Coordinate(0, 1)),
        SOUTH_WEST("sw", Coordinate(-1, 1)),
        WEST("w", Coordinate(-1, 0)),
        NORTH_WEST("nw", Coordinate(0, -1)),
        NORTH_EAST("ne", Coordinate(1, -1));

        companion object {
            private val initialsMap by lazy { values().associateBy { it.initials } }

            fun fromInitials(initials: String) = initialsMap[initials]
        }
    }
}
