fun findEncryptionWeakness(fileName: String, preambleLength: Int): Long {

    val invalid = findInvalid(fileName, preambleLength)

    val readFile = readFile(fileName)

    return (2..preambleLength).asSequence().map { size ->
        readFile
            .windowed(size, 1)
            .filter { it.sum() == invalid }
    }
        .filterNot { it.isEmpty() }
        .flatten()
        .map { it.minOrNull()!! + it.maxOrNull()!! }
        .first()
}

fun findInvalid(fileName: String, preambleLength: Int): Long {

    val input = readFile(fileName)
    return input
        .filterIndexed { index, it ->
            index >= preambleLength && !anySumTo(
                input.subList(index - preambleLength, index),
                it
            )
        }
        .first()
}

fun anySumTo(subList: List<Long>, value: Long): Boolean {

    fun allCombinations(value: Long) =
        subList
            .filterNot { it == value }
            .map { Pair(value, it) }

    return subList
        .flatMap(::allCombinations)
        .any { it.first + it.second == value }
}


private fun readFile(file: String) = { }::class.java
    .getResourceAsStream(file)
    .bufferedReader()
    .readLines()
    .map { it.toLong() }
