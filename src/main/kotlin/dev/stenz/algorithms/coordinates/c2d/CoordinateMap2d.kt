package dev.stenz.algorithms.coordinates.c2d

class CoordinateMap2d(
    var xRange: IntRange? = null,
    var yRange: IntRange? = null,
    var wrapX: Boolean = false,
    var wrapY: Boolean = false
) {
    fun setBoundariesToCoordinates(coordinates: List<Coordinate2d>) {
        if (coordinates.isEmpty()) throw RuntimeException("coordinates can not be empty")
        this.xRange = coordinates.minOf(Coordinate2d::x)..coordinates.maxOf(Coordinate2d::x)
        this.yRange = coordinates.minOf(Coordinate2d::y)..coordinates.maxOf(Coordinate2d::y)
    }

    fun setMaxBoundariesToCoordinatesNotNegative(coordinates: List<Coordinate2d>) {
        this.xRange = 0..coordinates.maxOf(Coordinate2d::x)
        this.yRange = 0..coordinates.maxOf(Coordinate2d::y)
    }

    fun createCoordinate(x: Int, y: Int) = Coordinate2d(x, y, this)

    fun checkIfInBounds(coordinate2d: Coordinate2d): Boolean {
        return !((xRange != null && coordinate2d.x !in xRange!!) ||
                 (yRange != null && coordinate2d.y !in yRange!!))
    }

    fun wrapAndFilterBoundaries(coordinate2d: Coordinate2d): Coordinate2d? {
        var newY: Int = coordinate2d.y
        var newX: Int = coordinate2d.x

        if (wrapX && xRange != null) {
            val range = xRange!!
            val xDiff = range.last - range.first + 1
            newX = (coordinate2d.x - range.first) % xDiff + range.first
            if (newX < range.first) newX += xDiff
        }

        if (wrapY && yRange != null) {
            val range = yRange!!
            val yDiff = range.last - range.first + 1
            newY = (coordinate2d.y - range.first) % yDiff + range.first
            if (newY < range.first) newY += yDiff
        }

        if (xRange != null && !xRange!!.contains(newX)) return null
        if (yRange != null && !yRange!!.contains(newY)) return null


        return Coordinate2d(newX, newY)
    }

    companion object {
        fun List<Coordinate2d>.wrapAndFilterBoundaries(map: CoordinateMap2d) = this.mapNotNull { map.wrapAndFilterBoundaries(it) }
    }
}