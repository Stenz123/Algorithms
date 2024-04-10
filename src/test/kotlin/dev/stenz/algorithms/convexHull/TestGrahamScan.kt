package dev.stenz.algorithms.convexHull

import dev.stenz.algorithms.coordinates.c2d.Coordinate2d
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class TestGrahamScan {

    @Test
    fun testEmptyList() {
        val points = emptyList<Coordinate2d>()
        val result = GrahamScan.solve(points)
        assertEquals(emptyList(), result)
    }

    @Test
    fun testSinglePoint() {
        val points = listOf(Coordinate2d(0, 0))
        val result = GrahamScan.solve(points)
        assertEquals(listOf(Coordinate2d(0, 0)), result)
    }

    @Test
    fun testTwoPoints() {
        val points = listOf(Coordinate2d(0, 0), Coordinate2d(1, 1))
        val result = GrahamScan.solve(points)
        assertEquals(listOf(Coordinate2d(0, 0), Coordinate2d(1, 1)), result)
    }

    @Test
    fun testCollinearPoints() {
        val points = listOf(
            Coordinate2d(0, 0),
            Coordinate2d(1, 1),
            Coordinate2d(2, 2),
            Coordinate2d(3, 3)
        )
        val result = GrahamScan.solve(points)
        assertEquals(listOf(Coordinate2d(0, 0), Coordinate2d(1, 1), Coordinate2d(2, 2), Coordinate2d(3, 3)), result)
    }

    @Test
    fun testAllPointsClockwise() {
        val points = listOf(
            Coordinate2d(0, 0),
            Coordinate2d(1, 0),
            Coordinate2d(1, 1),
            Coordinate2d(0, 1)
        )
        val result = GrahamScan.solve(points)
        assertEquals(listOf(Coordinate2d(0, 0), Coordinate2d(0, 1), Coordinate2d(1, 1), Coordinate2d(1, 0)), result)
    }

    @Test
    fun testAllPointsCounterclockwise() {
        val points = listOf(
            Coordinate2d(0, 0),
            Coordinate2d(0, 1),
            Coordinate2d(1, 1),
            Coordinate2d(1, 0)
        )
        val result = GrahamScan.solve(points)
        assertEquals(listOf(Coordinate2d(0, 0), Coordinate2d(0, 1), Coordinate2d(1, 1), Coordinate2d(1, 0)), result)
    }

}
