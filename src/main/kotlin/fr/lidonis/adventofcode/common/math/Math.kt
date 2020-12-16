package fr.lidonis.adventofcode.common.math

import kotlin.math.atan2
import kotlin.math.pow
import java.math.BigInteger

fun atan2(x: Int, y: Int) = atan2(x.toDouble(), y.toDouble())

infix fun Int.pow(i: Int) = this.toDouble().pow(i).toInt()

infix fun Long.modInverse(m: Long) = BigInteger.valueOf(this).modInverse(BigInteger.valueOf(m)).toLong()

fun lcm(x: Long, y: Long): Long {
    var a = x
    var b = y
    while (a != 0L) {
        a = (b % a).also { b = a }
    }
    return x / b * y
}

fun isPrime(num: Int): Boolean {
    var flag = false
    for (i in 2..num / 2) {
        if (num % i == 0) {
            flag = true
            break
        }
    }
    return !flag
}

fun multInv(a: Long, b: Long): Long {
    if (b == 1L) return 1L
    var aa = a
    var bb = b
    var x0 = 0L
    var x1 = 1L
    while (aa > 1) {
        val q = aa / bb
        var t = bb
        bb = aa % bb
        aa = t
        t = x0
        x0 = x1 - q * x0
        x1 = t
    }
    if (x1 < 0) x1 += b
    return x1
}

fun chineseRemainder(n: List<Long>, a: List<Long>): Long {
    val prod = n.fold(1L) { acc, i -> acc * i }
    var sum = 0L
    for (i in n.indices) {
        val p = prod / n[i]
        sum += a[i] * multInv(p, n[i]) * p
    }
    return sum % prod
}
