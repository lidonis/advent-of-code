package fr.lidonis.adventofcode.y2022.day7

private const val FREE_SPACE_FOR_UPDATE = 40_000_000

class NoSpaceLeftOnDevice(lines: List<String>) {

    private var dirs = listOf<String>()
    private var used = 0
    private val sizes = mutableMapOf<List<String>, Int>()

    init {

        fun List<String>.cd(dir: String) = when (dir) {
            ".." -> subList(0, size - 1)
            "/" -> listOf()
            else -> plus(dir)
        }

        fun List<String>.addSize(size: Int) = indices.map { subList(0, it + 1) }.forEach { path ->
            sizes[path] = (sizes[path] ?: 0) + size
        }

        for (line in lines) when {
            line.startsWith("$ cd ") -> dirs = dirs.cd(line.substringAfterLast(" "))
            else -> line.split(" ").first().toIntOrNull()?.also(dirs::addSize)?.also { used += it }
        }

        sizes.values
    }

    fun smallerThan(size: Int) = sizes.values.filter { it <= size }

    fun sizeOfDirectoryToDelete() = sizes.values.filter { it >= used - FREE_SPACE_FOR_UPDATE }.min()
}
