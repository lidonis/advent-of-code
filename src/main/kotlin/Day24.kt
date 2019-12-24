class Eris(private val bugs: List<Tile>) {
    constructor(input: String) : this(input.lines().mapIndexed { i, line ->
        line.mapIndexed { j, c ->
            Tile(c == '#', Position(j, i))
        }
    }.flatten())

    val biodiversityRating by lazy {
        bugs.mapIndexedNotNull { i, tile ->
            if(tile.bug) 2.pow(i) else null
        }.sum()
    }

    data class Tile(val bug: Boolean, val position: Position)
}