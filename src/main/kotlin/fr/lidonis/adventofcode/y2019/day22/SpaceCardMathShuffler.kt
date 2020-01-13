package fr.lidonis.adventofcode.y2019.day22

import fr.lidonis.adventofcode.common.modInverse
import java.math.BigInteger

class SpaceCardMathShuffler(private val size: Long) {

    fun dealIntoNewStack() = ModularFunction(-1, size - 1, size)

    fun cut(n: Long) = ModularFunction(1, n, size)

    fun dealWithIncrement(n: Long) = ModularFunction(n.modInverse(size), 0, size)

    fun shuffle(instructions: String) = instructions.lines().map {
        when {
            it == "deal into new stack" -> dealIntoNewStack()
            it.startsWith("cut") -> cut(it.split(" ").last().toLong())
            it.startsWith("deal with increment") -> dealWithIncrement(it.split(" ").last().toLong())
            else -> error("Unknown technique")
        }
    }.reduce { acc, coefficient -> acc * coefficient }

    data class ModularFunction(val increment: BigInteger, val offset: BigInteger, val size: BigInteger) {

        constructor(a: Long, b: Long, size: Long) : this(
            BigInteger.valueOf(a),
            BigInteger.valueOf(b),
            BigInteger.valueOf(size)
        )

        operator fun times(other: ModularFunction) =
            ModularFunction(
                (increment * other.increment).mod(size),
                (increment * other.offset + offset).mod(size),
                size
            )

        // power using geometric series
        fun pow(n: Long): ModularFunction {
            val newIncrement = increment.modPow(BigInteger.valueOf(n), size)
            return ModularFunction(
                newIncrement,
                offset * (BigInteger.ONE - newIncrement) * (BigInteger.ONE - increment).modInverse(size),
                size
            )
        }

        fun compute(index: Long): BigInteger = (increment * BigInteger.valueOf(index) + offset).mod(size)
    }
}
