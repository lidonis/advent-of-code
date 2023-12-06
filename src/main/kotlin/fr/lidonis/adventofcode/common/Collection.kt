package fr.lidonis.adventofcode.common

val <T> Iterable<T>.head: T
    get() = first()

val <T> Iterable<T>.tail: List<T>
    get() = drop(1)

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

fun <T> Collection<T>.combine(size: Int): List<List<T>> {
    return when {
        this.isEmpty() -> emptyList()
        size == 1 -> this.map(::listOf)
        else -> this.tail.combine(size - 1).map { listOf(this.head) + it } + this.tail.combine(size)
    }
}

fun Collection<Int>.multiply() = this.reduce(Int::times)

fun Collection<Long>.multiply() = this.reduce(Long::times)

fun Sequence<Int>.multiply() = this.reduce(Int::times)
