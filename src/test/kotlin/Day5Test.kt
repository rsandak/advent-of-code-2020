import org.junit.Test
import kotlin.test.assertEquals

internal class Day5Test {

    @Test
    fun testDecodeSeatID() {
        assertEquals(357, Seat("FBFBBFFRLR").decodeSeatID())
        assertEquals(567, Seat("BFFFBBFRRR").decodeSeatID())
        assertEquals(119, Seat("FFFBBBFRRR").decodeSeatID())
        assertEquals(820, Seat("BBFFBBFRLL").decodeSeatID())
    }
}
