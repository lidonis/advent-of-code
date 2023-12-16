package fr.lidonis.adventofcode.y2020.day25

private const val DIVISOR = 20201227L
private const val SUBJECT_NUMBER = 7L

class ComboBreaker(private val subjectNumber: Long = SUBJECT_NUMBER) {

    fun findLoopSize(publicKey: Long): Int = transforms().takeWhile { it != publicKey }.count()

    private fun transforms() = generateSequence(1L) { it * subjectNumber % DIVISOR }

    fun computeEncryptionKey(loopSize: Int) = transforms().elementAt(loopSize)
}
