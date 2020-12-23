package fr.lidonis.adventofcode.common.math

import fr.lidonis.adventofcode.common.head

class Matrix<T>(val values: List<List<T>>) : List<List<T>> by values {
    val row get() = values.size

    val column get() = values.head.size

    private fun flip() = Matrix(values.map { it.reversed() })

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

    private fun rotate(): Matrix<T> {
        val transpose = MutableList(column) { MutableList(row) { values[0][0] } }
        for (i in 0 until row) {
            for (j in 0 until column) {
                transpose[j][i] = values[i][j]
            }
        }
        return Matrix(transpose).flip()
    }

    fun forEachIndexed(action: (Int, Int, T) -> Unit): Unit {
        for (x in 0 until row) {
            for (y in 0 until column) {
                action(x, y, this[x][y])
            }
        }
    }
}
