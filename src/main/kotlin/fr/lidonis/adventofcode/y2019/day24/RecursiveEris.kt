package fr.lidonis.adventofcode.y2019.day24

private const val WINDOW_SIZE = 3

data class RecursiveEris(val erises: List<Eris>) {

    fun evolve(minutes: Int) = (1..minutes).fold(this) { reris, _ -> reris.evolve() }

    private fun evolve(): RecursiveEris {
        val evolved =
            (listOf(Eris(emptySet()).recursiveEvolve(Eris(emptySet()), erises.first())) +
                    (listOf(Eris(emptySet())) + erises + Eris(emptySet())).windowed(
                        WINDOW_SIZE,
                        partialWindows = true
                    ).map {
                        when (it.size) {
                            WINDOW_SIZE -> it[1].recursiveEvolve(it[0], it[2])
                            WINDOW_SIZE - 1 -> it[1].recursiveEvolve(it[0], Eris(emptySet()))
                            else -> Eris(emptySet())
                        }
                    }).toMutableList()
        if (evolved.first() == Eris(emptySet())) {
            evolved.removeAt(0)
        }
        if (evolved.first() == Eris(emptySet())) {
            evolved.removeAt(0)
        }
        if (evolved.last() == Eris(emptySet())) {
            evolved.removeAt(evolved.size - 1)
        }
        if (evolved.last() == Eris(emptySet())) {
            evolved.removeAt(evolved.size - 1)
        }
        return RecursiveEris(evolved)
    }

    fun countBugs() = erises.map(Eris::countBugs).sum()
}