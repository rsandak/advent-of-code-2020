import org.junit.Test
import kotlin.test.assertEquals

internal class Day7Test {

    @Test
    fun test() {
        assertEquals(4, countContainingBag("day7test.txt", "shiny gold"))
        assertEquals(155, countContainingBag("day7.txt", "shiny gold"))
    }

    @Test
    fun test2() {
        assertEquals(32, countRequiredInside("day7test.txt", "shiny gold"))
        assertEquals(126, countRequiredInside("day7test2.txt", "shiny gold"))
        assertEquals(54803, countRequiredInside("day7.txt", "shiny gold"))
    }
}
