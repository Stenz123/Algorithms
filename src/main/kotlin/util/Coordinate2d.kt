package util


/**
 * Top y-1 ↑ | Bottom y+1 ↓ | Left x-1 ← | Right x+1 →
 */
data class Coordinate2d(var x: Int, var y: Int) {

    override fun toString(): String {
        return "(x:$x|y:$y)"
    }

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
    )

    fun get4Neighbours(): List<Coordinate2d> = listOf(top(), bottom(), right(), left())
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


}