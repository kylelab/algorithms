import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/**
 * Problem : https://www.acmicpc.net/problem/2178
 * TimeComplexity:
 * Algorithim: BFS
 * Solution
 *
 *
 *
 *
 *
 *
 */
private var n: Int = 0
private var m: Int = 0
private lateinit var graph: Array<IntArray>
private lateinit var visited: Array<IntArray>

val nearRow = intArrayOf(-1, 1, 0, 0)
val nearCol = intArrayOf(0, 0, -1, 1)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val (nInput, mInput) = br.readLine().split(" ").map { it.toInt() }
    n = nInput
    m = mInput

    graph = Array(n) {
        br.readLine().map { it.toString().toInt() }.toIntArray()
    }
    br.close()

    visited = Array(n) { IntArray(m) { 0 } }

    val ans = bfs(Point(0, 0), graph, visited, n, m)
    println(ans)
}

fun bfs(point: Point, graph: Array<IntArray>, visited: Array<IntArray>, n: Int, m: Int): Int {
    val queue = LinkedList<Point>()
    queue.offer(point)
    visited[point.row][point.col] = 1

    while (queue.isNotEmpty()) {
        val now = queue.poll()

        for (i in 0..<4) {
            val nr = nearRow[i] + now.row
            val nc = nearCol[i] + now.col

            if (!(nr in 0..<n && nc in 0..<m)) continue

            if (visited[nr][nc] == 0 && graph[nr][nc] == 1) {
                visited[nr][nc] = visited[now.row][now.col] + 1
                queue.offer(Point(nr, nc))
            }
        }

    }
    return visited[n - 1][m - 1]
}

data class Point(val row: Int, val col: Int)

