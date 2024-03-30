import util.Coordinate2d
import kotlin.test.Test
import kotlin.test.assertEquals

class TestFail {

    @Test
    fun testThatWillFail() {
        throw RuntimeException()
    }

}
