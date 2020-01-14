package fr.lidonis.adventofcode.common

import java.math.BigInteger
import kotlin.math.atan2
import kotlin.math.pow

fun atan2(x: Int, y: Int) = atan2(x.toDouble(), y.toDouble())

infix fun Int.pow(i: Int) = this.toDouble().pow(i).toInt()

infix fun Long.modInverse(m: Long) = BigInteger.valueOf(this).modInverse(BigInteger.valueOf(m)).toLong()
