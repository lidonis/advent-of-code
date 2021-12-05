package fr.lidonis.adventofcode.common.math

private val NUMBER_REGEX = """\d+""".toRegex()

// from exercism https://exercism.org/tracks/kotlin/exercises/matrix/
class Matrix(private val matrixAsStrings: List<String>) {

    constructor(matrixAsString:String) : this(matrixAsString.lines())

    private val parsed = matrixAsStrings
        .map { line ->
            NUMBER_REGEX.findAll(line)
                .map(MatchResult::value)
                .map(String::toInt)
                .toList()
        }

    fun column(colNr: Int): List<Int> = parsed.map { it[colNr - 1] }

    fun row(rowNr: Int): List<Int> = parsed[rowNr - 1]

}
