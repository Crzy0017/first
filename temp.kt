package com.example.myapplication
import java.util.*
import kotlin.math.*

internal class Point {
    var x = 0.0    // x - value
    var y = 0.0   // y - value

    override fun toString(): String {
        return "($x;$y)"
    }

    fun printPoint() {
        print(this.toString())
    }

    fun movePoint(a: Double, b: Double) {
        x += a
        y += b
    }

    fun setPoint(a: Double, b: Double) {
        x = a
        y = b
    }

    constructor() {
        println("Enter x-coordinate:")
        val scan1 = Scanner(System.`in`)
        x = scan1.nextInt().toDouble()
        println("Enter y-coordinate")
        val scan2 = Scanner(System.`in`)
        y = scan2.nextInt().toDouble()
    }

    constructor(a: Double, b: Double) {
        x = a
        y = b
    }

    fun equalsPoint(p_0: Point): Boolean {
        return x == p_0.x && y == p_0.y
    }

    fun collinear(p1: Point, p2: Point): Boolean {
        val slope1 = (p1.y - y)/(p1.x - x)
        val slope2 = (p2.y - y)/(p2.x - x)
        return if (slope1 == slope2) {
            println("Points are Collinear")
            true
        } else {
            println("Points are not collinear")
            false
        }
    }
}

internal class Triangle {
    var P1: Point? = null
        private set
    var P2: Point? = null
        private set
    var P3: Point? = null
        private set

    constructor() {
        val P1 = Point()
        val P2 = Point()
        val P3 = Point()
        if (!P3.collinear(P1, P2)) {
            this.P1 = Point(P1.x, P1.y)
            this.P2 = Point(P2.x, P2.y)
            this.P3 = Point(P3.x, P3.y)
        } else {
            println("The points are collinear")
            this.P1 = Point(-1, 0)
            this.P2 = Point(0, 1)
            this.P3 = Point(1, 1)
        }
    }

    constructor(ax: Double, ay: Double, bx: Double, by: Double, cx: Double, cy: Double) {
        val P1 = Point(p1x, p1y)
        val P2 = Point(p2x, p2y)
        val P3 = Point(p3x, p3y)
        if (!P3.collinear(P1, P2)) {
            this.P1 = Point(p1x, p1y)
            this.P2 = Point(p2x, p2y)
            this.P3 = Point(p1x, p1y)
        } else {
            println("The points are collinear")
            this.P1 = Point(0, 0)
            this.P2 = Point(2, 2)
            this.P2 = Point(2, 0)
        }
    }

    override fun toString(): String {
        return "Triangle with coordinate-points: P1=" + a.toString() + "; P2=" + b.toString() + "; P3=" + c.toString() + ";"
    }

    fun print() {
        print(this.toString())
    }

    fun Perimeter(): Double {
        val side1 = sqrt((P1.x - P2.x) * (P1.x - P2.x) + (P1.y - P2.y) * (P1.y - P2.y))
        val side2 = sqrt((P2.x - P3.x) * (P2.x - P3.x) + (P2.y - P3.y) * (P2.y - P3.y))
        val side3 = sqrt((P3.x - P1.x) * (P3.x - P1.x) + (P3.y - P1.y) * (P3.y - P1.y))
        return side1 + side2 + side3
    }

    fun Area(): Double {
        val side1 = sqrt((P1.x - P2.x) * (P1.x - P2.x) + (P1.y - P2.y) * (P1.y - P2.y))
        val side2 = sqrt((P2.x - P3.x) * (P2.x - P3.x) + (P2.y - P3.y) * (P2.y - P3.y))
        val side3 = sqrt((P3.x - P1.x) * (P3.x - P1.x) + (P3.y - P1.y) * (P3.y - P1.y))
        val semiP = (side1 + side2+ side3)/2
        return sqrt(semiP*(semiP-side1)*(semiP-side2)*(semiP-side3))
    }

    fun setP1(x: Double, y: Double) {
        val p_0 = Point(x, y)
        if (!p_0.collinear(P2, P3)) {
            P1 = p_0
        } else {
            println("Not allowed to change")
        }
    }

    fun setP2(x: Double, y: Double) {
        val p_0 = Point(x, y)
        if (!p_0.collinear(P1, P3)) {
            P2 = p_0
        } else {
            println("Not allowed to change")
        }
    }

    fun setP3(x: Double, y: Double) {
        val p_0 = Point(x, y)
        if (!p_0.collinear(P1, P2)) {
            P3 = p_0
        } else {
            println("Not allowed to change")
        }
    }

    fun rotate(deg: Double) {
        val newP1x = P1.x * cos(deg * Math.PI / 180) - P1.y * sin(deg * Math.PI / 180)
        val newP1y = P1.x * sin(deg * Math.PI / 180) - P1.y * cos(deg * Math.PI / 180)
        val newP2x = P2.x * cos(deg * Math.PI / 180) - P2.y * sin(deg * Math.PI / 180)
        val newP2y = P2.x * sin(deg * Math.PI / 180) - P2.y * cos(deg * Math.PI / 180)
        val newP3x = P3.x * cos(deg * Math.PI / 180) - P3.y * sin(deg * Math.PI / 180)
        val newP3y = P3.x * sin(deg * Math.PI / 180) - P3.y * cos(deg * Math.PI / 180)
        P1 = Point(newP1x, newP1y)
        P2 = Point(newP2x, newP2y)
        P3 = Point(newP3x, newP3y)
    }
}
