package fr.lidonis.adventofcode.y2020.day14

import java.util.BitSet

private const val VALUES_BIT_SIZE = 36
private val MASK_REGEX = Regex("^mask = ([X01]{36})$")
private val MEMORY_REGEX = Regex("""^mem\[(\d+)] = (\d+)$""")

sealed class DockingData(private val program: List<String>) {

    protected val memory = mutableMapOf<Long, Long>()

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

    abstract fun readMask(mask: String)

    abstract fun writeMemory(address: Long, value: Long)
}

class DockingDataV1(program: List<String>) : DockingData(program) {

    private var bitSet1 = BitSet(VALUES_BIT_SIZE)
    private var bitSet0 = BitSet(VALUES_BIT_SIZE)

    override fun readMask(mask: String) {
        bitSet1 = BitSet(VALUES_BIT_SIZE)
        bitSet0 = BitSet(VALUES_BIT_SIZE)
        mask.reversed().forEachIndexed { index, c ->
            when (c) {
                '1' -> bitSet1.set(index, true)
                '0' -> bitSet0.set(index, true)
            }
        }
    }

    override fun writeMemory(address: Long, value: Long) {
        val bitSet = BitSet.valueOf(longArrayOf(value))
        bitSet.or(bitSet1)
        bitSet.andNot(bitSet0)
        memory[address] = bitSet.toLongArray()[0]
    }
}

class DockingDataV2(program: List<String>) : DockingData(program) {

    private var mask = ""

    override fun readMask(mask: String) {
        this.mask = mask.reversed()
    }

    override fun writeMemory(address: Long, value: Long) {
        var addresses = listOf(BitSet.valueOf(longArrayOf(address)))
        mask.forEachIndexed { index, c ->
            when (c) {
                '1' -> addresses.forEach { it.set(index) }
                'X' -> addresses = floatingIndex(addresses, index)
            }
        }
        addresses.forEach { memory[it.toLongArray()[0]] = value }
    }

    private fun floatingIndex(addresses: List<BitSet>, index: Int) =
        addresses.onEach { it.clear(index) } + addresses.map { it.clone() as BitSet }.onEach { it.set(index, true) }
}
