import org.junit.Test
import kotlin.test.assertEquals

internal class Day3Test {

    @Test
    fun testCountTrees() {
        assertEquals(336, exploreForest("day3test.txt"))
        assertEquals(3510149120, exploreForest("day3.txt"))
    }
}
