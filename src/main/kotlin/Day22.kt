import java.math.BigInteger
import java.util.*

class SpaceCardDeck(private val cards: List<Int>) : Iterable<Int> by cards {
    constructor(size: Int) : this((0 until size).toList())

    fun dealIntoNewStack() = SpaceCardDeck(cards.reversed())

    fun cut(n: Int) = SpaceCardDeck(cards.toMutableList().also { Collections.rotate(it, -n) })

    fun dealWithIncrement(n: Int): SpaceCardDeck {
        val newCards = IntArray(cards.size)
        var i = 0
        for (card in cards) {
            newCards[i] = card
            i = (i + n) % cards.size
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

data class SpaceCardInverseShuffler(private val size: Int, val index: Int, val value: Int) {
    fun dealIntoNewStack() = SpaceCardInverseShuffler(size, index, size - 1 - value)
    fun cut(n: Int) = SpaceCardInverseShuffler(size, Math.floorMod(index + n, size), value)
    fun dealWithIncrement(n: Int) =
        SpaceCardInverseShuffler(
            size, when {
                (index == 0) -> 0
                n == size - 1 -> n * index % size
                else -> size - n * index % size
            }, value
        )

    fun shuffle(instructions: String) = instructions.lines().map {
        when {
            it == "deal into new stack" -> { deck: SpaceCardInverseShuffler ->
                deck.dealIntoNewStack()
            }
            it.startsWith("cut") -> { deck: SpaceCardInverseShuffler ->
                deck.cut(it.split(" ").last().toInt())
            }
            it.startsWith("deal with increment") -> { deck: SpaceCardInverseShuffler ->
                deck.dealWithIncrement(it.split(" ").last().toInt())
            }
            else -> { _ -> error("Unknown technique") }
        }
    }.foldRight(this) { function, acc -> function(acc) }
}

class SpaceCardMathShuffler(private val size: Int, private val index: Int, private val value: Int) {

    fun dealIntoNewStack() = compute(-1, size - 1)

    fun cut(n: Int) = compute(1, loop(n))

    fun dealWithIncrement(n: Int) = compute(n, 0)

    private fun compute(a: Int, b: Int) = loop(loop(a * index) + b)

    private fun loop(n: Int) = (n % size + size) % size

}




operator fun BigInteger.rem(m: Long): BigInteger = this.mod(BigInteger.valueOf(m))
operator fun BigInteger.times(other: Long): BigInteger = this.times(BigInteger.valueOf(other))
operator fun BigInteger.times(other: Int): BigInteger = this.times(BigInteger.valueOf(other.toLong()))
operator fun BigInteger.plus(other: Int): BigInteger = this.plus(BigInteger.valueOf(other.toLong()))
operator fun Int.minus(other: BigInteger): BigInteger = BigInteger.valueOf(this.toLong()).minus(other)
fun BigInteger.modPow(e: Long, m: Long): BigInteger = this.modPow(BigInteger.valueOf(e), BigInteger.valueOf(m))
fun BigInteger.modInverse(m: Long): BigInteger = this.modInverse(BigInteger.valueOf(m))
fun Long.modInverse(m: Long): BigInteger = BigInteger.valueOf(this).modInverse(BigInteger.valueOf(m))
fun Int.modInverse(m: Long): BigInteger = BigInteger.valueOf(this.toLong()).modInverse(BigInteger.valueOf(m))