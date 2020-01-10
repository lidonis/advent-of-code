package fr.lidonis.adventofcode.y2019.day17

data class MovementRules(val mainRoutine: String, val functionA: String, val functionB: String, val functionC: String) {

    companion object {
        fun from(path: List<String>): MovementRules {
            // TODO https://github.com/ephemient/aoc2019/blob/kt/src/main/kotlin/io/github/ephemient/aoc2019/Day17.kt
            return MovementRules(
                "A,C,A,C,B,C,B,A,C,B",
                "R,4,R,10,R,8,R,4",
                "R,4,L,12,R,6,L,12",
                "R,10,R,6,R,4"
            )
        }
    }
}