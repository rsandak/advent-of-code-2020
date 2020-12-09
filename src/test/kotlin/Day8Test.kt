import org.junit.Test
import kotlin.test.assertEquals

internal class Day8Test {

    @Test
    fun test() {
        assertEquals(5, loadGame("day8test.txt").play().value)
        assertEquals(1548, loadGame("day8.txt").play().value)
    }

    @Test
    fun testFix() {
        assertEquals(8, GameFixer(loadGame("day8test.txt")).fix()!!.play().value)
        assertEquals(1375, GameFixer(loadGame("day8.txt")).fix()!!.play().value)
    }
}
