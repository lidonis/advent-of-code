package fr.lidonis.adventofcode.common

import java.math.BigInteger
import kotlin.math.pow

fun Int.pow(i: Int) = this.toDouble().pow(i).toInt()

fun Long.modInverse(m: Long) = BigInteger.valueOf(this).modInverse(BigInteger.valueOf(m)).toLong()