import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.max

/**
 * Problem : https://www.acmicpc.net/problem/7575
 * TimeComplexity:
 * Algorithim: BFS
 * Solution
 * 토마토
 * 인접한 부분을 탐색하면서 탐색의 대상이 되는 부분 1씩 카운트 한다.
 *
 * 익어있는 토마토의 포인트를 따로 저장해둔다.
 * 한번 돌고 나서 저장해둔다.
 *
 *
 *
 */

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val (m, n) = br.readLine().split(" ").map { it.toInt() }

    val graph: Array<IntArray> = Array(n) {
        br.readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    val targetList: MutableList<Point7576> = mutableListOf()

    for (i in 0..<n) {
        for (j in 0..<m) {
            val now = graph[i][j]
            if (now == 1) {
                targetList.add(Point7576(i, j))
            }
        }
    }

    val visited: Array<IntArray> = Array(n) { IntArray(m) }

    bfs(targetList, visited, graph, m, n)

    var max = 0
    for (i in 0..<n) {
        for (j in 0..<m) {
            max = max(max, visited[i][j])
            if (visited[i][j] == 0 && graph[i][j] == 0) {   // 방문한적도 없고, 토마토가 있는 자리 였는지 판단
                println(-1)
                return
            }
        }
    }

    println(max - 1)
}

private val nearRow7576 = intArrayOf(1, -1, 0, 0)
private val nearCol7576 = intArrayOf(0, 0, -1, 1)

private data class Point7576(val row: Int, val col: Int)

private fun bfs(starts: List<Point7576>, visited: Array<IntArray>, graph: Array<IntArray>, m: Int, n: Int) {
    val queue = LinkedList<Point7576>()

    for (start in starts) {
        queue.offer(start)
        visited[start.row][start.col] = 1
    }

    while (queue.isNotEmpty()) {
        val now = queue.poll()

        for (i in 0..<4) {
            val nr = now.row + nearRow7576[i]
            val nc = now.col + nearCol7576[i]
            if (nr !in 0..<n || nc !in 0..<m) continue

            if (visited[nr][nc] == 0 && graph[nr][nc] == 0) {
                visited[nr][nc] = visited[now.row][now.col] + 1
                queue.offer(Point7576(nr, nc))
            }
        }
    }
}

