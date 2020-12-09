import org.junit.Test
import kotlin.test.assertEquals

internal class Day9Test {

    @Test
    fun testFindingInvalid() {
        assertEquals(127, findInvalid("day9test.txt", 5))
        assertEquals(217430975, findInvalid("day9.txt", 25))
    }

    @Test
    fun testEncryptionWeakness() {
        assertEquals(62, findEncryptionWeakness("day9test.txt", 5))
        assertEquals(28509180, findEncryptionWeakness("day9.txt", 25))
    }
}
