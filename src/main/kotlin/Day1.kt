fun main() {
    val expanses = readExpansesFromFile()

    for ((index, expanse) in expanses.withIndex()) {
        val otherExpanses = expanses.subList(index, expanses.size)
        for ((index2, expanse2) in otherExpanses.withIndex()) {
            for (expanse3 in otherExpanses.subList(index2, otherExpanses.size)) {
                if (sumTo2020(expanse, expanse2, expanse3)) {
                    println(expanse * expanse2 * expanse3)
                }
            }
        }
    }
}

private fun sumTo2020(expanse: Int, expanse2: Int, expanse3: Int) = expanse + expanse2 + expanse3 == 2020

private fun readExpansesFromFile() = { }::class.java
    .getResourceAsStream("expanses.txt")
    .bufferedReader()
    .readLines()
    .map { it.toInt() }

