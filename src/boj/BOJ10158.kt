package boj

import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Problem : https://www.acmicpc.net/problem/10158
 * TimeComplexity: O(n), O(1)
 * Type: 시간복잡도
 * Solution
 * x축과 y축으로 나눠서 생각해보면 2w/2h 주기로 반복되는 것을 알 수 있다.
 */
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (w, h) = br.readLine().split(" ").map { it.toInt() }
    val (p, q) = br.readLine().split(" ").map { it.toInt() }
    val t = br.readLine().toInt()
    br.close()

    val (currX, currY) =
        solution3(p, q, t, w, h)

    println("$currX $currY")
}

private fun solution1(
    p: Int,
    q: Int,
    t: Int,
    w: Int,
    h: Int
): Pair<Int, Int> {
    var currX = p
    var currY = q

    var deltaX = 1
    var deltaY = 1
    for (i in 1..t) {
        if (currX == w) {
            deltaX = -1
        } else if (currX == 0) {
            deltaX = 1
        }

        if (currY == h) {
            deltaY = -1
        } else if (currY == 0) {
            deltaY = 1
        }

        currX += deltaX
        currY += deltaY
    }
    return Pair(currX, currY)
}

private fun solution2(
    p: Int,
    q: Int,
    t: Int,
    w: Int,
    h: Int
): Pair<Int, Int> {
    var currX = p
    var currY = q

    var deltaX = 1
    var deltaY = 1

    val timeW = t % (2 * w)
    for (i in 1..timeW) {
        if (currX == w) {
            deltaX = -1
        } else if (currX == 0) {
            deltaX = 1
        }
        currX += deltaX
    }

    val timeH = t % (2 * h)
    for (i in 1..timeH) {
        if (currY == h) {
            deltaY = -1
        } else if (currY == 0) {
            deltaY = 1
        }
        currY += deltaY
    }
    return Pair(currX, currY)
}

private fun solution3(
    p: Int,
    q: Int,
    t: Int,
    w: Int,
    h: Int
): Pair<Int, Int> {
    var currentX = (p + t) % (2 * w)
    var currentY = (q + t) % (2 * h)

    if (currentX > w) {
        currentX = w - (currentX - w)
    }

    if (currentY > h) {
        currentY = h - (currentY - h)
    }

    return Pair(currentX, currentY)
}
