package dev.stenz.algorithms

import dev.stenz.algorithms.coordinates.c2d.Coordinate2d
import kotlin.collections.ArrayDeque

object Flood {

    /*
    * Returns a list of the coordinates that is surrounded by a border.
    * If the start point is in the map, every point from the map is going to be filled, else the map is going to be the border.
    * To define a limit on an infinite map you have to use maps.
    */
    fun basicFill(map: List<Coordinate2d>, startPoint: Coordinate2d, fill8Directions: Boolean = false): Set<Coordinate2d> {
        val inScopeSubject: (Coordinate2d) -> Boolean = if (startPoint in map) { c -> c in map } else { c -> c !in map }

        val result = hashMapOf(startPoint to true)
        val queue = ArrayDeque<Coordinate2d>()
        queue.add(startPoint)
        while (queue.isNotEmpty()) {
            val curr = queue.removeLast()
            val neighbours = ((if (fill8Directions) curr.get8Neighbours() else curr.get4Neighbours())
                .filter { inScopeSubject(it) }
                .filter { it !in result })
            result.putAll(neighbours.map{it to true})
            queue.addAll(neighbours)
        }
        return result.map { it.key }.toSet()
    }
}