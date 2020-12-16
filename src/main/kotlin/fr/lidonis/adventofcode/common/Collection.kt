package fr.lidonis.adventofcode.common

fun <T> List<T>.permute(): List<List<T>> {
    if (this.size == 1) return listOf(this)
    val permutations = mutableListOf<List<T>>()
    val toInsert = this[0]
    for (permutation in this.drop(1).permute()) {
        for (i in 0..permutation.size) {
            val newPermutation = permutation.toMutableList()
            newPermutation.add(i, toInsert)
            permutations.add(newPermutation)
        }
    }
    return permutations
}

fun <T> List<T>.combine(size: Int): List<Set<T>> {
    return when {
        this.isEmpty() -> emptyList()
        size == 1 -> this.map(::setOf)
        else -> this.drop(1).combine(size - 1).map { setOf(this.first()) + it } + this.drop(1).combine(size)
    }
}
