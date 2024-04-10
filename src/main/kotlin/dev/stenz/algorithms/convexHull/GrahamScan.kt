package dev.stenz.algorithms.convexHull

import dev.stenz.algorithms.coordinates.c2d.Coordinate2d

object GrahamScan {

    fun solve(points: List<Coordinate2d>): List<Coordinate2d> {
        if (points.isEmpty())return listOf()

        //lowest y left
        val p1: Coordinate2d = points.minBy { it.y }.let { lowestY ->
            points.filter { it.y == lowestY.y }.minBy { it.x }
        }
        val anglePoint = Coordinate2d(p1.x - 1, p1.y)

        val pointsWithAngleSorted =
            points.filter { it != p1 }.map { it to Coordinate2d.calculateAngle(anglePoint, p1, it) }
                .sortedBy { it.second }.map { it.first }
        val stack = ArrayDeque<Coordinate2d>()
        stack.add(p1)

        for (point in pointsWithAngleSorted) {
            while (stack.size > 1 && Coordinate2d.orientation(stack.secondLast(), stack.last(), point) < 0) {
                stack.removeLast()
            }
            stack.add(point)
        }

        return stack.toList()
    }

    private fun <T> ArrayDeque<T>.secondLast(): T {
        val last = this.removeLast()
        val res = this.last()
        this.add(last)
        return res
    }

}