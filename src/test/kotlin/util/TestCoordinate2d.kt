package util

import dev.stenz.algorithms.util.Coordinate2d
import kotlin.test.Test
import kotlin.test.assertEquals

class TestCoordinate2dTest {

    @Test
    fun testGet8Neighbours() {
        val coordinate = Coordinate2d(3, 4)
        val expectedNeighbors = listOf(
            Coordinate2d(3, 3), Coordinate2d(3, 5),
            Coordinate2d(4, 4), Coordinate2d(2, 4),
            Coordinate2d(2, 3), Coordinate2d(4, 3),
            Coordinate2d(2, 5), Coordinate2d(4, 5)
        )
        assertEquals(expectedNeighbors, coordinate.get8Neighbours())
    }

    @Test
    fun testGet4Neighbours() {
        val coordinate = Coordinate2d(3, 4)
        val expectedNeighbors = listOf(
            Coordinate2d(3, 3), Coordinate2d(3, 5),
            Coordinate2d(4, 4), Coordinate2d(2, 4)
        )
        assertEquals(expectedNeighbors, coordinate.get4Neighbours())
    }

    @Test
    fun testTop() {
        val coordinate = Coordinate2d(3, 4)
        val expectedTop = Coordinate2d(3, 3)
        assertEquals(expectedTop, coordinate.top())
    }

    @Test
    fun testBottom() {
        val coordinate = Coordinate2d(3, 4)
        val expectedBottom = Coordinate2d(3, 5)
        assertEquals(expectedBottom, coordinate.bottom())
    }

    @Test
    fun testLeft() {
        val coordinate = Coordinate2d(3, 4)
        val expectedLeft = Coordinate2d(2, 4)
        assertEquals(expectedLeft, coordinate.left())
    }

    @Test
    fun testRight() {
        val coordinate = Coordinate2d(3, 4)
        val expectedRight = Coordinate2d(4, 4)
        assertEquals(expectedRight, coordinate.right())
    }

    @Test
    fun testTopRight() {
        val coordinate = Coordinate2d(3, 4)
        val expectedTopRight = Coordinate2d(4, 3)
        assertEquals(expectedTopRight, coordinate.topRight())
    }

    @Test
    fun testBottomLeft() {
        val coordinate = Coordinate2d(3, 4)
        val expectedBottomLeft = Coordinate2d(2, 5)
        assertEquals(expectedBottomLeft, coordinate.bottomLeft())
    }

    @Test
    fun testTopLeft() {
        val coordinate = Coordinate2d(3, 4)
        val expectedTopLeft = Coordinate2d(2, 3)
        assertEquals(expectedTopLeft, coordinate.topLeft())
    }

    @Test
    fun testBottomRight() {
        val coordinate = Coordinate2d(3, 4)
        val expectedBottomRight = Coordinate2d(4, 5)
        assertEquals(expectedBottomRight, coordinate.bottomRight())
    }
}
