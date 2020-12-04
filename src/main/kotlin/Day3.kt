fun exploreForest(forestFile: String): Long {
    val slides = listOf(
        Slide(1, 1),
        Slide(3, 1),
        Slide(5, 1),
        Slide(7, 1),
        Slide(1, 2)
    )

    return slides
        .map { slide -> Forest(readFile(forestFile)).traverse(slide) }
        .reduce { acc, it -> acc * it }
}

class Forest(mapRows: List<String>) {
    private val TREE: Char = '#'

    private val squares: Array<CharArray> = Array(mapRows.size) { i ->
        mapRows[i].toCharArray()
    }

    private val dimensions = ForestDimensions(squares[0].size, squares.size)

    fun traverse(slide: Slide): Long {
        val toboggan = Toboggan(dimensions)

        while (isInForest(toboggan)) {

            if (treeEncountered(toboggan)) {
                toboggan.encounterTree()
            }

            toboggan.move(slide)

        }

        return toboggan.encounteredTrees
    }

    private fun treeEncountered(position: Toboggan) = squares[position.positionY][position.positionX] == TREE

    private fun isInForest(toboggan: Toboggan) = toboggan.positionY < dimensions.height
}

class Toboggan(private val forestDimensions: ForestDimensions) {
    var positionY: Int = 0
    var positionX: Int = 0

    var encounteredTrees: Long = 0

    fun move(slide: Slide) {
        positionX = (positionX + slide.right) % forestDimensions.width
        positionY += slide.down
    }

    fun encounterTree() = encounteredTrees++
}

class ForestDimensions(val width: Int, val height: Int)

class Slide(val right: Int, val down: Int)

private fun readFile(file: String) = { }::class.java
    .getResourceAsStream(file)
    .bufferedReader()
    .readLines()
    .filter { it.isNotEmpty() }
