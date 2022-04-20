package com.example.myapplication

import java.util.*
import kotlin.math.pow
import kotlin.math.sqrt

internal class Point {
    var x // абсцисса точки
            = 0.0
    var y // ордината точки
            = 0.0

    // возвращает строку с описанием точки
    override fun toString(): String {
        return "($x;$y)"
    }

    // выводит на экран описание точки
    fun printPoint() {
        print(this.toString())
    }

    // метод перемещает точку на указанный вектор
    fun movePoint(a: Double, b: Double) {
        x += a
        y += b
    }

    // метод изменяет координаты точки на указанные
    fun setPoint(a: Double, b: Double) {
        x = a
        y = b
    }

    // конструктор по умолчанию, создающий точку в начале координат
    constructor() {
        println("Vvedite ordinatu tochki:")
        val sc1 = Scanner(System.`in`)
        if (sc1.hasNextInt()) {
            x = sc1.nextInt().toDouble()
        }
        println("Vvedite abstissu tochki:")
        val sc2 = Scanner(System.`in`)
        if (sc2.hasNextInt()) {
            y = sc2.nextInt().toDouble()
        }
    }

    // конструктор, создающий точку с указанными координатами
    constructor(a: Double, b: Double) {
        x = a
        y = b
    }

    // метод вычисляющий расстояние между точками
    fun length(p: Point): Double {
        return sqrt((p.x - x).pow(2.0) + (p.y - y).pow(2.0))
    }

    // метод проверяющий совпадают ли точки
    fun equalsPoint(p: Point): Boolean {
        return x == p.x && y == p.y
    }

    fun collinear(p1: Point?, p2: Point?): Boolean {
        val distx1 = x - p1!!.x
        val disty1 = y - p1.y
        val distx2 = p1.x - p2!!.x
        val disty2 = p1.y - p2.y
        val k1 = disty1 / distx1
        val k2 = disty2 / distx2
        val c1 = y - k1 * x
        val c2 = p2.y - k2 * p2.x
        return if (k1 == k2 && c1 == c2) {
            println("Kollinearni")
            true
        } else {
            println("Ne kollinearni")
            false
        }
    }
}

internal class Triangle {
    var a: Point? = null
        private set
    var b: Point? = null
        private set
    var c: Point? = null
        private set

    constructor() {
        val a = Point()
        val b = Point()
        val c = Point()
        if (!a.collinear(b, c)) {
            this.a = Point(a.x, a.y)
            this.b = Point(b.x, b.y)
            this.c = Point(c.x, c.y)
        } else {
            println("Tochki, zadannije vami, yavljyajytsa kollinearnimi, treugoljnik postroit nevozmojno")
            this.a = Point(-1, 0)
            this.b = Point(0, 1)
            this.c = Point(1, 1)
        }
    }

    constructor(ax: Double, ay: Double, bx: Double, by: Double, cx: Double, cy: Double) {
        val a = Point(ax, ay)
        val b = Point(bx, by)
        val c = Point(cx, cy)
        if (!a.collinear(b, c)) {
            this.a = Point(ax, ay)
            this.b = Point(bx, by)
            this.c = Point(cx, cy)
        } else {
            println("Tochki, zadannije vami, yavljyajytsa kollinearnimi, treugoljnik postroit nevozmojno")
            this.a = Point(-1, 0)
            this.b = Point(0, 1)
            this.c = Point(1, 1)
        }
    }

    override fun toString(): String {
        return "Treygoljnik s koordinatami vershin: A=" + a.toString() + "; B=" + b.toString() + "; C=" + c.toString() + ";"
    }

    fun print() {
        print(this.toString())
    }

    fun Perimeter(): Double {
        val a = Math.sqrt((a!!.x - b!!.x) * (a!!.x - b!!.x) + (a!!.y - b!!.y) * (a!!.y - b!!.y))
        val b = Math.sqrt((b!!.x - b!!.x) * (b!!.x - c!!.x) + (b!!.y - c!!.y) * (b!!.y - c!!.y))
        val c = Math.sqrt((a.x - c!!.x) * (a.x - c!!.x) + (a.y - c!!.y) * (a.y - c!!.y))
        /*System.out.println();
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);*/return a + b + c
    }

    fun S(): Double {
        val a = Math.sqrt((a!!.x - b!!.x) * (a!!.x - b!!.x) + (a!!.y - b!!.y) * (a!!.y - b!!.y))
        val b = Math.sqrt((b!!.x - b!!.x) * (b!!.x - c!!.x) + (b!!.y - c!!.y) * (b!!.y - c!!.y))
        //double c = (A.x-C.x)*(A.x-C.x)+(A.y-C.y)*(A.y-C.y);
        val h = Math.abs(a * a - b / 2 * b / 2)
        return h * b / 2
    }

    fun setA(x: Double, y: Double) {
        val p = Point(x, y)
        if (!p.collinear(b, c)) {
            a = p
        } else {
            println("Tochku pomenjat neljza, t. k. ona budet kollinearna k drugim vershinam treugolnika")
        }
    }

    fun setB(x: Double, y: Double) {
        val p = Point(x, y)
        if (!p.collinear(a, c)) {
            b = p
        } else {
            println("Tochku pomenjat neljza, t. k. ona budet kollinearna k drugim vershinam treugolnika")
        }
    }

    fun setC(x: Double, y: Double) {
        val p = Point(x, y)
        if (!p.collinear(a, b)) {
            c = p
        } else {
            println("Tochku pomenjat neljza, t. k. ona budet kollinearna k drugim vershinam treugolnika")
        }
    }

    fun rotate(deg: Double) {
        val rad: Double
        val cos: Double
        val sin: Double
        if (deg == 90.0) {
            cos = 0.0
            sin = 1.0
        } else {
            rad = deg * Math.PI / 180
            cos = Math.cos(rad)
            sin = Math.sin(rad)
        }
        val newAx = a!!.x * cos - a!!.y * sin
        val newAy = a!!.x * sin - a!!.y * cos
        val newBx = b!!.x * cos - b!!.y * sin
        val newBy = b!!.x * sin - b!!.y * cos
        val newCx = c!!.x * cos - c!!.y * sin
        val newCy = c!!.x * sin - c!!.y * cos
        a!!.x = newAx
        a!!.y = newAy
        b!!.x = newBx
        b!!.y = newBy
        c!!.x = newCx
        c!!.y = newCy
        /*A.x = A.x*Math.cos(rad)-A.y*Math.sin(rad);
		A.y = A.x*Math.sin(rad)-A.y*Math.cos(rad);
		B.x = B.x*Math.cos(rad)-B.y*Math.sin(rad);
		B.y = B.x*Math.sin(rad)-B.y*Math.cos(rad);
		C.x = C.x*Math.cos(rad)-C.y*Math.sin(rad);
		C.y = C.x*Math.sin(rad)-C.y*Math.cos(rad);*/
        //Triangle tr = new Triangle(A,B,C);
        //return tr;
    }
}
