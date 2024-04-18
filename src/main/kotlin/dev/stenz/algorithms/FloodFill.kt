package dev.stenz.algorithms

import dev.stenz.algorithms.coordinates.c2d.Coordinate2d

object FloodFill {

    /*
    * Returns a list of the coordinates that is surrounded by a border.
    * If the start point is in the map, every point from the map is going to be filled, else the map is going to be the border.
    * To define a limit on an infinite map you have to use maps.
    */
    fun basicFill(
        map: List<Coordinate2d>,
        startPoint: Coordinate2d,
        fill8Directions: Boolean = false
    ): Set<Coordinate2d> {
        val inScopeSubject: (Coordinate2d) -> Boolean = if (startPoint in map) { c -> c in map } else { c -> c !in map }

        val result = hashMapOf(startPoint to true)
        val queue = ArrayDeque<Coordinate2d>()
        queue.add(startPoint)
        while (queue.isNotEmpty()) {
            val curr = queue.removeLast()
            val neighbours = ((if (fill8Directions) curr.get8Neighbours() else curr.get4Neighbours())
                .filter { inScopeSubject(it) }
                .filter { it !in result })
            result.putAll(neighbours.map { it to true })
            queue.addAll(neighbours)
        }
        return result.map { it.key }.toSet()
    }


    private fun rectangleFill(map: HashSet<Coordinate2d>, startPoint: Coordinate2d): Set<Coordinate2d> {
        val mapClone = HashSet(map)
        privRectangleFill(mapClone, startPoint.copy())
        mapClone.removeAll(map)
        return mapClone.toSet()
    }

    //http://www.adammil.net/blog/v126_A_More_Efficient_Flood_Fill.html
    private fun privRectangleFill(map: HashSet<Coordinate2d>, startPoint: Coordinate2d) {
        val curr = startPoint.copy()
        while (true) {
            val dCord = curr.copy()

            while (!map.contains(curr.top())) curr.moveTop()
            while (!map.contains(curr.left())) curr.moveLeft()
            if (dCord == curr) break
        }
        rectangleFillCore(map, curr)
    }

    private fun rectangleFillCore(map: HashSet<Coordinate2d>, startPoint: Coordinate2d) {
        var lastRowLength = 0
        val curr = startPoint.copy()
        do {
            var rowLength = 0
            var sx: Int = startPoint.x

            if (lastRowLength != 0 && map.contains(curr)) {
                do {
                    if (--lastRowLength == 0) return
                } while (map.contains(curr.moveRight()))
                sx = curr.x;
            } else {
                while (!map.contains(curr.left())) {
                    rowLength++
                    lastRowLength++
                    sx--
                    map.add(curr.left())
                    curr.moveLeft()

                    if (!map.contains(curr.top())) {
                        privRectangleFill(map, curr.copy().moveTop())
                    }
                }
            }
            while (!map.contains(Coordinate2d(sx, curr.y))) {
                map.add(Coordinate2d(sx, curr.y)) //CLEANUP THIS PART
                rowLength++
                sx++
            }

            if (rowLength < lastRowLength) {
                val end = curr.x + lastRowLength
                while (++sx < end) {
                    if (!map.contains(Coordinate2d(sx, curr.y))) rectangleFillCore(map, Coordinate2d(sx, curr.y));
                }
            }
            else if (rowLength > lastRowLength) {
                var ux = curr.x + lastRowLength
                while (++ux < sx) {
                    if (!map.contains(Coordinate2d(ux ,curr.y-1))) privRectangleFill(map, Coordinate2d(ux, curr.y-1))
                }
            }
            lastRowLength = rowLength
            curr.moveBottom()
        } while (lastRowLength != 0)

    }
}