import org.junit.Test
import kotlin.test.assertEquals

internal class Day6Test {

    @Test
    fun test() {
        assertEquals(11, sumQuestionsAnyoneAnsweredYes("day6test.txt"))
        assertEquals(6530, sumQuestionsAnyoneAnsweredYes("day6.txt"))
    }

    @Test
    fun test2() {
        assertEquals(6, sumQuestionsEveryoneAnsweredYes("day6test.txt"))
        assertEquals(3323, sumQuestionsEveryoneAnsweredYes("day6.txt"))
    }
}
