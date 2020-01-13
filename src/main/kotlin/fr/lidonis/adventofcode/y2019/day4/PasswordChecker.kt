package fr.lidonis.adventofcode.y2019.day4

object PasswordChecker {

    fun check1(password: Int) = password.decompose().run {
        neverDecrease() && containsAtLeastOneFollowingDuplicate()
    }

    fun check2(password: Int) = password.decompose().run {
        neverDecrease() && containsAtLeastOneCouple()
    }

    private fun Int.decompose(): List<Int> {
        val digits = ArrayList<Int>()
        var n = this
        while (n > 0) {
            digits.add(n % 10)
            n /= 10
        }
        return digits.reversed()
    }

    private fun <T : Comparable<T>> List<T>.neverDecrease() = this.zipWithNext().all { it.first <= it.second }

    private fun <T> List<T>.containsAtLeastOneFollowingDuplicate() = this.zipWithNext().any { it.first == it.second }

    private fun <T> List<T>.containsAtLeastOneCouple() = this.groupingBy { it }.eachCount().any { (_, it) -> it == 2 }
}
