package dev.stenz.algorithms

import dev.stenz.algorithms.coordinates.c2d.CoordinateMap2d
import dev.stenz.algorithms.coordinates.c2d.CoordinateMap2d.Companion.draw
import org.junit.jupiter.api.Test

class TestPathfinding {
    @Test
    fun testDjikstra() {
        //TODO: Proper tests

        val input = "S..############################\n" +
                "...#..#..#.....#...........#..#\n" +
                "#..#..#..#..#######..####..#..#\n" +
                "#........#..#..#.....#........#\n" +
                "#######..#..#..####..####..#..#\n" +
                "#.....#........#..#..#..#..#..#\n" +
                "####..####..####..####..#..#..#\n" +
                "#..#..#..............#.....#..#\n" +
                "#..#..##########..####..#..####\n" +
                "#..#.....#..............#..#..#\n" +
                "#..#..####..#######..####..#..#\n" +
                "#..#.....#..#...........#.....#\n" +
                "#..#..#..#..####..#..#######..#\n" +
                "#.....#..#..#.....#.....#.....#\n" +
                "#..#######..####..##########..#\n" +
                "#........#..#.....#........#..#\n" +
                "#..#######..#######..#######..#\n" +
                "#........#...........#..#.....#\n" +
                "####..#..#..#..#######..#..#..#\n" +
                "#.....#.....#........#.....#..#\n" +
                "############################..F"

        val nodes = CoordinateMap2d.parse(input, listOf('.', 'S', 'F'))
        val start = CoordinateMap2d.parse(input, 'S')[0]
        val end = CoordinateMap2d.parse(input, 'F')[0]

        nodes.draw(PathFinding.djikstras(nodes, start, end) { it.get4Neighbours().filter { node -> nodes.contains(node) } })
    }
}