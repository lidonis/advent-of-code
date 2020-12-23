package fr.lidonis.adventofcode.y2020.day20

import fr.lidonis.adventofcode.common.head

class Matrix<T>(val values: List<List<T>>) : List<List<T>> by values {

    val row get() = values.size
    val column get() = values.head.size

    fun allOrientations() = listOf(
        this,
        this.rotate(),
        this.rotate().rotate(),
        this.rotate().rotate().rotate(),
        this.flip(),
        this.flip().rotate(),
        this.flip().rotate().rotate(),
        this.flip().rotate().rotate().rotate(),
    )

    private fun flip() = Matrix(values.map { it.reversed() })

    private fun rotate(): Matrix<T> {
        val transpose = MutableList(column) { MutableList(row) { values[0][0] } }
        for (i in 0 until row) {
            for (j in 0 until column) {
                transpose[j][i] = values[i][j]
            }
        }
        return Matrix(transpose).flip()
    }

}
