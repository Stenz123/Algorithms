package dev.stenz.algorithms.coordinates.c2d

import kotlin.test.Test
import kotlin.test.assertEquals

class TestCoordinateMap2d {

    @Test
    fun checkWrapAround() {
        val map = CoordinateMap2d(
            wrapX = true,
            wrapY = true,
            xRange = 2..10,
            yRange = 1..5
        )

        assertEquals(Coordinate2d(2, 1), map.newCoordinate(11, 1).wrapAndFilterBoundaries())
        assertEquals(Coordinate2d(2, 5), map.newCoordinate(2, 100).wrapAndFilterBoundaries())
    }

    @Test
    fun checkWrapAroundWithNegativeValues() {
        val map = CoordinateMap2d(
            wrapX = true,
            wrapY = true,
            xRange = -10..-5,
            yRange = -15..5
        )

        assertEquals(Coordinate2d(-9, -15), map.newCoordinate(-15, 6).wrapAndFilterBoundaries())
    }

    @Test
    fun checkOutOfBounds() {
        val map = CoordinateMap2d(xRange = 0..10, yRange = 5..20)

        assertEquals(null, map.newCoordinate(10, 0).wrapAndFilterBoundaries())
        assertEquals(Coordinate2d(0, 5), map.newCoordinate(0, 5).wrapAndFilterBoundaries())
    }

    @Test
    fun checkIfIsOutOfBounds() {
        val map = CoordinateMap2d(xRange = 0..5, yRange = -100..-90)

        assertEquals(true, map.checkIfInBounds(Coordinate2d(5, -90)))
        assertEquals(true, map.checkIfInBounds(Coordinate2d(0, -100)))
        assertEquals(false, map.checkIfInBounds(Coordinate2d(6, -90)))
        assertEquals(false, map.checkIfInBounds(Coordinate2d(3, -89)))
    }

    @Test
    fun setBoundariesToMax() {
        val coordinates = listOf(
            Coordinate2d(0,0),
            Coordinate2d(10,5)
        )
        val map = CoordinateMap2d()
        map.setBoundariesToCoordinates(coordinates)

        assertEquals(true, map.checkIfInBounds(Coordinate2d(5, 5)))
        assertEquals(false, map.checkIfInBounds(Coordinate2d(0, 6)))
        assertEquals(false, map.checkIfInBounds(Coordinate2d(20, 20)))
    }

    @Test
    fun setBoundariesToMaxStartingByZero() {
        val coordinates = listOf(
            Coordinate2d(10,5)
        )
        val map = CoordinateMap2d()
        map.setMaxBoundariesToCoordinatesNotNegative(coordinates)

        assertEquals(true, map.checkIfInBounds(Coordinate2d(5, 5)))
        assertEquals(false, map.checkIfInBounds(Coordinate2d(0, 6)))
        assertEquals(false, map.checkIfInBounds(Coordinate2d(20, 20)))
    }

}