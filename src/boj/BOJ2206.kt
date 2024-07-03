import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/**
 * Problem : https://www.acmicpc.net/problem/2206
 * TimeComplexity:
 * Algorithim: BFS
 * Solution
 * 벽 부수고 이동하기
 *
 * 최단 경로 = 칸의 갯수를 샌다 -> 탐색 차수를 샌다.
 *
 * 한 개의 벽을 부수고 이동하는 것?
 * 벽을 부수지 않았다면 부순것과 부수지 않은 것 두경우를 탐색한다.
 * 부순 유무는 삼중 배열로 표시한다.
 *
 * 방문하지 않은 상태가 기본 전제
 * 벽이 없는 상태에서 탐색-> 벽을 부순적이 있는지는 기존 now의 isBroken으로 계속 전달하면 된다.
 * 벽이 있다면 부수는 상태로 탐색
 *
 *
 *
 */

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m) = br.readLine().split(" ").map { it.toInt() }

    val graph: Array<IntArray> = Array(n) {
        br.readLine().map { it.toString().toInt() }.toIntArray()
    }

    val visited: Array<Array<IntArray>> = Array(n) { Array(m) { IntArray(2) } }

    val nearRow = intArrayOf(-1, 1, 0, 0)
    val nearCol = intArrayOf(0, 0, -1, 1)

    val queue = LinkedList<Point2206>()
    queue.offer(Point2206(0, 0, 0))
    visited[0][0][0] = 1

    while (queue.isNotEmpty()) {
        val now = queue.poll()
        if (now.row == n - 1 && now.col == m - 1) {
            println("${visited[n - 1][m - 1][now.isBroken]}")
            return
        }

        for (i in 0..<4) {
            val nr = now.row + nearRow[i]
            val nc = now.col + nearCol[i]
            if (nr !in 0..<n || nc !in 0..<m) continue

            if (visited[nr][nc][now.isBroken] == 0) {
                // 일반적인 벽이 없는 상태의 탐색
                if (graph[nr][nc] == 0) {
                    visited[nr][nc][now.isBroken] = visited[now.row][now.col][now.isBroken] + 1
                    queue.offer(Point2206(nr, nc, now.isBroken))
                }
                // 벽을 부술수 있다면 부수고 가보는 탐색
                else if (graph[nr][nc] == 1 && now.isBroken == 0) {
                    visited[nr][nc][1] = visited[now.row][now.col][0] + 1
                    queue.add(Point2206(nr, nc, 1))
                }
            }
        }
    }

    println("-1")
}


private data class Point2206(val row: Int, val col: Int, val isBroken: Int)








