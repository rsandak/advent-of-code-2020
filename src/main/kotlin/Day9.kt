class Decrypter(fileName: String, private val preambleLength: Int) {
    private val input = readFile(fileName)

    fun findInvalid(): Long {
        return input
            .filterIndexed { index, value -> index >= preambleLength && !isValid(value, index) }
            .first()
    }

    fun findEncryptionWeakness(): Long {

        val invalid = findInvalid()

        return (2..preambleLength).asSequence().map { size ->
            input
                .windowed(size, 1)
                .filter { it.sum() == invalid }
        }
            .filterNot { it.isEmpty() }
            .flatten()
            .map { it.minOrNull()!! + it.maxOrNull()!! }
            .first()
    }


    private fun isValid(value: Long, index: Int): Boolean {

        fun allCombinations(value: Long) =
            input.subList(index - preambleLength, index)
                .filterNot { it == value }
                .map { Pair(value, it) }

        return input.subList(index - preambleLength, index)
            .flatMap(::allCombinations)
            .any { it.first + it.second == value }
    }
}

private fun readFile(file: String) = { }::class.java
    .getResourceAsStream(file)
    .bufferedReader()
    .readLines()
    .map { it.toLong() }
