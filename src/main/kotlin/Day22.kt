import java.math.BigInteger
import java.util.*

class SpaceCardDeck(private val cards: List<Int>) : Iterable<Int> by cards {
    constructor(size: Int) : this((0 until size).toList())

    val size: Int get() = cards.size

    fun dealIntoNewStack() = SpaceCardDeck(cards.reversed())

    fun cut(n: Int) = SpaceCardDeck(cards.toMutableList().also { Collections.rotate(it, -n) })

    fun dealWithIncrement(n: Int): SpaceCardDeck {
        val newCards = IntArray(size)
        var i = 0
        for (card in cards) {
            newCards[i] = card
            i = (i + n) % size
        }
        return SpaceCardDeck(newCards.toList())
    }

    fun shuffle(instructions: String) = instructions.lines().map {
        when {
            it == "deal into new stack" -> { deck: SpaceCardDeck -> deck.dealIntoNewStack() }
            it.startsWith("cut") -> { deck: SpaceCardDeck -> deck.cut(it.split(" ").last().toInt()) }
            it.startsWith("deal with increment") -> { deck: SpaceCardDeck -> deck.dealWithIncrement(it.split(" ").last().toInt()) }
            else -> { _ -> error("Unknown technique") }
        }
    }.fold(this) { acc, function -> function(acc) }

    fun cardPosition(position: Int) = cards.indexOf(position)

    override fun toString(): String {
        return "SpaceCardDeck(cards=$cards)"
    }

}

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

fun Long.modInverse(m: Long) = BigInteger.valueOf(this).modInverse(BigInteger.valueOf(m)).toLong()
