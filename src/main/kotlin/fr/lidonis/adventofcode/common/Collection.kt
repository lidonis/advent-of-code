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
