package fr.lidonis.adventofcode.y2020.day14

import java.util.BitSet

private val MASK_REGEX = Regex("^mask = ([X01]{36})$")
private val MEMORY_REGEX = Regex("""^mem\[(\d+)] = (\d+)$""")

sealed class DockingData(private val program: List<String>) {

    protected val memory = mutableMapOf<Long, Long>()
    protected var mask = ""

    fun runProgram(): Long {
        program.forEach { line ->
            val match = MASK_REGEX.matchEntire(line)
            if (match != null) {
                readMask(match.destructured.component1())
            } else {
                MEMORY_REGEX.matchEntire(line)?.destructured?.let { (address, value) ->
                    writeMemory(address.toLong(), value.toLong())
                }
            }
        }
        return memory.values.sum()
    }

    open fun readMask(s: String) {
        mask = s.reversed()
    }

    protected abstract fun writeMemory(address: Long, value: Long)
}

class DockingDataV1(program: List<String>) : DockingData(program) {

    private var bitSet1 = BitSet(36)

    override fun readMask(s: String) {
        super.readMask(s)
        //TODO BISET AND ET AND NOT
        bitSet1 = BitSet(36)
        mask.forEachIndexed { index, c ->
            if (c == '1') {
                println(c); bitSet1.set(index, true); println(bitSet1)
            }
        }
        println("$mask $bitSet1")
    }

    override fun writeMemory(address: Long, value: Long) {
        val bitSet = BitSet.valueOf(longArrayOf(value))
        mask.forEachIndexed { index, c ->
            when (c) {
                '0' -> bitSet.set(index, false)
            }
        }
        bitSet.and(bitSet1)

        memory[address] = bitSet.toLongArray()[0]
    }
}

class DockingDataV2(program: List<String>) : DockingData(program) {

    override fun writeMemory(address: Long, value: Long) {
        var results = mutableListOf(BitSet.valueOf(longArrayOf(address)))
        mask.forEachIndexed { index, c ->
            when (c) {
                '1' -> results.forEach { it.set(index) }
                'X' -> {
                    val t1 =
                        results.map { BitSet.valueOf(it.toLongArray()) }.onEach { it.set(index, true) }
                            .toMutableList()
                    val t2 =
                        results.map { BitSet.valueOf(it.toLongArray()) }.onEach { it.clear(index) }
                    t1.addAll(t2)
                    results = t1
                }
            }
        }
        results.forEach { memory[it.toLongArray()[0]] = value }
    }
}
