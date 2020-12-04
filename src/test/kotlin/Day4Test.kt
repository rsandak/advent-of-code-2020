import org.junit.Test
import kotlin.test.assertEquals

internal class Day4Test {

    @Test
    fun testPasswords() {
        assertEquals(2,  countPassports("day4documents.txt"))
        assertEquals(260,  countPassports("day4.txt"))
    }

    @Test
    fun testValidPasswords() {
        assertEquals(0,  countValidPassports("day4invalidPasswords.txt"))
        assertEquals(4,  countValidPassports("day4validPasswords.txt"))
        assertEquals(153,  countValidPassports("day4.txt"))
    }
}
