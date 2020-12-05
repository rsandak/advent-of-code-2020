class Seat(code: String) {
    private val rowCode = code.take(7)
    private val columnCode = code.takeLast(3)

    fun decodeSeatID(): Int {
        val row = decodePosition(rowCode, Range.createRowRange())
        val column = decodePosition(columnCode, Range.createColumnRange())

        return row * 8 + column
    }

    private fun decodePosition(positionCode: String, range: Range): Int {
        if (positionCode.length == 1) {
            return if (range.isLowerHalf(positionCode[0])) range.min else range.max
        }

        return decodePosition(
            positionCode.substring(1, positionCode.length),
            range.trimTo(positionCode[0])
        )
    }
}

class Range(val min: Int, val max: Int) {
    private val LOWER_HALF_REGIONS = listOf('F', 'L')

    companion object {
        fun createRowRange() = Range(0, 127)
        fun createColumnRange() = Range(0, 7)
    }

    fun trimTo(letter: Char): Range {
        return if (isLowerHalf(letter)) lowerHalf() else upperHalf()
    }

    fun isLowerHalf(letter: Char) = LOWER_HALF_REGIONS.contains(letter)

    private fun lowerHalf(): Range {
        return Range(min, (max + min) / 2)
    }

    private fun upperHalf(): Range {
        return Range((max + min) / 2 + 1, max)
    }
}

private fun assignSeats() = readFile("day5.txt")
    .map { Seat(it) }
    .map { it.decodeSeatID() }

private fun findMySeat(assignSeats: List<Int>): Int {
    val minSeatID = assignSeats.minOrNull()!!
    val maxSeatID = assignSeats.maxOrNull()!!

    return (minSeatID..maxSeatID)
        .minus(assignSeats)
        .first()
}

private fun readFile(file: String) = { }::class.java
    .getResourceAsStream(file)
    .bufferedReader()
    .readLines()

fun main() {
    val assignSeats = assignSeats()
    val maxSeatID = assignSeats.maxOrNull()!!
    println(maxSeatID)

    println(findMySeat(assignSeats))
}
