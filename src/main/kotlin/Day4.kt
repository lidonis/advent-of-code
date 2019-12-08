import java.util.*


fun main() {
    val range = InputReader("day4.txt").asIntRange()
    val count1 = count(range, PasswordChecker::check1)
    println("$count1 different passwords with first check")
    val count2 = count(range, PasswordChecker::check2)
    print("$count2 different passwords with second check")
}

private fun count(range: IntRange, predicate: (Int) -> Boolean): Int {
    return range.filter(predicate).count()
}

object PasswordChecker {

    fun check1(password: Int): Boolean {
        val decomposed = password.decompose()
        return decomposed.neverDecrease() && decomposed.containsFollowingDuplicate()
    }

    fun check2(password: Int): Boolean {
        val decomposed = password.decompose()
        return decomposed.neverDecrease() && onlyCouple(decomposed)
    }

    private fun onlyCouple(decomposed: List<Int>) =
        decomposed.groupingBy { it }.eachCount().any { (_, it) -> it == 2 }

}

fun Int.decompose(): List<Int> {
    val digits = ArrayList<Int>()
    var n = this
    while (n > 0) {
        digits.add(n % 10)
        n /= 10
    }
    return digits.reversed()
}

fun <T : Comparable<T>> List<T>.neverDecrease() = this.zipWithNext().all { it.first <= it.second }

fun <T> List<T>.containsFollowingDuplicate() = this.zipWithNext().any { it.first == it.second }

