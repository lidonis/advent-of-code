package fr.lidonis.adventofcode.common

val <T> Iterable<T>.tail: List<T>
    get() = drop(1)

val <T> List<T>.head: T
    get() = first()

fun <T> List<T>.permute(): List<List<T>> {
    if (this.size == 1) return listOf(this)
    val permutations = mutableListOf<List<T>>()
    for (permutation in this.tail.permute()) {
        for (i in 0..permutation.size) {
            val newPermutation = permutation.toMutableList()
            newPermutation.add(i, head)
            permutations.add(newPermutation)
        }
    }
    return permutations
}

fun <T> List<T>.combine(size: Int): List<Set<T>> {
    return when {
        this.isEmpty() -> emptyList()
        size == 1 -> this.map(::setOf)
        else -> this.tail.combine(size - 1).map { setOf(this.first()) + it } + this.tail.combine(size)
    }
}
