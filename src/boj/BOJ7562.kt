import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/**
 * Problem : https://www.acmicpc.net/problem/7562
 * TimeComplexity:
 * Algorithim: BFS
 * Solution
 * 나이트의 이동
 *
 *
 *
 *
 *
 */


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()

    val graphLengths = IntArray(n)
    val startPoints: MutableList<Point7562> = mutableListOf()
    val endPoints: MutableList<Point7562> = mutableListOf()

    repeat(n) { index ->
        graphLengths[index] = br.readLine().toInt()
        val (sRow, sCol) = br.readLine().split(" ").map { it.toInt() }
        val (eRow, eCol) = br.readLine().split(" ").map { it.toInt() }
        startPoints.add(Point7562(sRow, sCol))
        endPoints.add(Point7562(eRow, eCol))
    }
    br.close()

    repeat(n) {
        val m = graphLengths[it]
        val visited: Array<IntArray> = Array(m) { IntArray(m) }

        println(bfs(startPoints[it], endPoints[it], m, visited))
    }
}

private val nearRow7562 = intArrayOf(1, 2, 2, 1, -1, -2, -2, -1)
private val nearCol7562 = intArrayOf(-2, -1, 1, 2, 2, 1, -1, -2)

private fun bfs(start: Point7562, end: Point7562, m: Int, visited: Array<IntArray>): Int {
    val queue = LinkedList<Point7562>()
    queue.offer(start)

    visited[start.row][start.col] = 1
    while (queue.isNotEmpty()) {
        val now = queue.poll()
        if (now == end) {
            break
        }

        for (i in 0..<8) {
            val nr = now.row + nearRow7562[i]
            val nc = now.col + nearCol7562[i]
            if (nr !in 0..<m || nc !in 0..<m) continue
            if (visited[nr][nc] == 0) {
                visited[nr][nc] = visited[now.row][now.col] + 1
                queue.offer(Point7562(nr, nc))
            }
        }
    }

    return visited[end.row][end.col] - 1
}

private data class Point7562(val row: Int, val col: Int)