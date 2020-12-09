import org.junit.Test
import kotlin.test.assertEquals

internal class Day9Test {

    @Test
    fun testFindingInvalid() {
        assertEquals(127, Decrypter("day9test.txt", 5).findInvalid())
        assertEquals(217430975, Decrypter("day9.txt", 25).findInvalid())
    }

    @Test
    fun testEncryptionWeakness() {
        assertEquals(62, Decrypter("day9test.txt", 5).findEncryptionWeakness())
        assertEquals(28509180, Decrypter("day9.txt", 25).findEncryptionWeakness())
    }
}
