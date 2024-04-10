package dev.stenz.algorithms.coordinates.c2d

import dev.stenz.algorithms.coordinates.c2d.CoordinateMap2d.Companion.wrapAndFilterBoundaries
import kotlin.math.acos
import kotlin.math.pow
import kotlin.math.sqrt


/**
 * Top y-1 ↑ | Bottom y+1 ↓ | Left x-1 ← | Right x+1 →
 */
data class Coordinate2d(var x: Int, var y: Int, var map2d: CoordinateMap2d = CoordinateMap2d()) {

    fun top() = Coordinate2d(x, y - 1)
    fun bottom() = Coordinate2d(x, y + 1)
    fun left() = Coordinate2d(x - 1, y)
    fun right() = Coordinate2d(x + 1, y)

    fun topRight() = Coordinate2d(x + 1, y - 1)
    fun bottomLeft() = Coordinate2d(x - 1, y + 1)
    fun topLeft() = Coordinate2d(x - 1, y - 1)
    fun bottomRight() = Coordinate2d(x + 1, y + 1)

    fun get8Neighbours(): List<Coordinate2d> = listOf(
        top(),
        bottom(),
        right(),
        left(),
        topLeft(),
        topRight(),
        bottomLeft(),
        bottomRight()
    ).wrapAndFilterBoundaries(map2d)

    fun get4Neighbours(): List<Coordinate2d> = listOf(
        top(),
        bottom(),
        right(),
        left()
    ).wrapAndFilterBoundaries(map2d)

    fun wrapAndFilterBoundaries() = map2d.wrapAndFilterBoundaries(this)

    override fun toString(): String {
        return "(x:$x|y:$y)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Coordinate2d

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }

    companion object {
        fun calculateAngle(a: Coordinate2d, b: Coordinate2d, c: Coordinate2d): Double {
            val ba = doubleArrayOf((a.x - b.x).toDouble(), (a.y - b.y).toDouble()) // vector b to a
            val bc = doubleArrayOf((c.x - b.x).toDouble(), (c.y - b.y).toDouble()) // vector b to c

            val dotProduct = ba[0] * bc[0] + ba[1] * bc[1]
            val magnitudeBa = sqrt(ba[0].pow(2) + ba[1].pow(2))
            val magnitudeBc = sqrt(bc[0].pow(2) + bc[1].pow(2))

            return Math.toDegrees(acos(dotProduct / (magnitudeBa * magnitudeBc)))
        }

        /*
        * 0 --> p, q and r are collinear
        * 1 --> Clockwise
        * -1 --> Counterclockwise
        */
        fun orientation(p1: Coordinate2d, p2: Coordinate2d, p3: Coordinate2d): Int {
            val slope = (p2.y - p1.y) * (p3.x - p2.x) - (p2.x - p1.x) * (p3.y - p2.y)
            return when {
                slope == 0 -> 0 // collinear
                slope > 0 -> 1 // clockwise
                else -> -1 // counterclockwise
            }        }

    }
}
