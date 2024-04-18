package dev.stenz.algorithms.coordinates.c2d

import dev.stenz.algorithms.util.ConsoleColors

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

    fun newCoordinate(x: Int, y: Int) = Coordinate2d(x, y, this)

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
        fun parse(input: List<String>, positiveChars: List<Char>) = positiveChars.map { it to parse(input, it) }
        fun parse(input: String, positiveChars: List<Char>) = positiveChars.map {parse(input, it) }.flatten()
        fun parse(input: String, positiveChar: Char): List<Coordinate2d> = parse(input.split("\n").filter(String::isNotEmpty), positiveChar)
        fun parse(input: List<String>, positiveChar: Char): List<Coordinate2d> {
            val res = mutableListOf<Coordinate2d>()

            val map = CoordinateMap2d(input[0].indices, input.indices)
            for (y in map.yRange!!) {
                for (x in map.xRange!!) {
                    if (input[y][x] == positiveChar) {
                        res.add(map.newCoordinate(x, y))
                    }
                }
            }
            return res
        }

        fun List<Coordinate2d>.wrapAndFilterBoundaries(map: CoordinateMap2d) =
            this.mapNotNull { map.wrapAndFilterBoundaries(it) }

        fun List<Coordinate2d>.draw(colored: List<Coordinate2d> = listOf()) {
            val minX = this.minOf(Coordinate2d::x)
            val maxX = this.maxOf(Coordinate2d::x)
            val minY = this.minOf(Coordinate2d::y)
            val maxY = this.maxOf(Coordinate2d::y)

            //for (y in (minY..maxY)) print(y) TODO: Make numbers be printed vertically

            print('┎')
            repeat((maxX - minX + 1) * 2) { print('━') }
            println()
            for (y in (minY..maxY)) {
                print('│')
                for (x in (minX..maxX)) {
                    if (colored.contains(
                            Coordinate2d(
                                x,
                                y
                            )
                        )
                    ) print(ConsoleColors.GREEN + "▅" + ConsoleColors.RESET + "│")
                    else if (this.contains(Coordinate2d(x, y))) print("▅│")
                    else print(" │")
                }
                println()
            }
        }
    }
}