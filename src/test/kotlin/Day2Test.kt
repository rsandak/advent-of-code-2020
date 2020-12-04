import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class Day2Test {

    @Test
    fun testPasswordPolicy() {
        assertTrue(OldPasswordPolicy("1-3 a: abcde").isValid())
        assertFalse(OldPasswordPolicy("1-3 b: cdefg").isValid())
        assertTrue(OldPasswordPolicy("2-9 c: ccccccccc").isValid())
    }

    @Test
    fun testNewPasswordPolicy() {
        assertTrue(NewPasswordPolicy("1-3 a: abcde").isValid())
        assertFalse(NewPasswordPolicy("1-3 b: cdefg").isValid())
        assertFalse(NewPasswordPolicy("2-9 c: ccccccccc").isValid())
    }

    @Test
    fun testAllPasswords() {
        assertEquals(2, PasswordChecker("day2test.txt").run(
            createPolicy = { input -> OldPasswordPolicy(input) }
        ))
    }

    @Test
    fun testAllPasswordsForNewPolicy() {
        assertEquals(1, PasswordChecker("day2test.txt").run(
            createPolicy = { input -> NewPasswordPolicy(input) }
        ))
    }
}
