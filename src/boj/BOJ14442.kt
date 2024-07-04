import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/**
 * Problem : https://www.acmicpc.net/problem/14442
 * TimeComplexity:
 * Algorithim: BFS
 * Solution
 * 벽 부수고 이동하기2
 *
 * 최단 경로 = 칸의 갯수를 샌다 -> 탐색 차수를 샌다.
 *
 * 여러 개의 벽을 부수고 이동하는 것?
 * 벽을 부수지 않은 것과 정해진 갯수까지 부순 것을 탐색
 * 시간이 많이 걸릴거 같긴한데.. 해보자
 * 부순 유무는 삼중 배열로 표시한다.
 *
 * 방문하지 않은 상태가 기본 전제
 * 벽이 없는 상태에서 탐색-> 부순 벽의 갯수를 기존 now의 isBroken으로 계속 전달하면 된다.
 * 벽이 있다면 부수는 상태로 탐색
 *
 *
 *
 */

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m, k) = br.readLine().split(" ").map { it.toInt() }
    val board: Array<IntArray> = Array(n) {
        br.readLine().map { it.toString().toInt() }.toIntArray()
    }

    val visited: Array<Array<IntArray>> = Array(n) { Array(m) { IntArray(k + 2) } }

    val nearRow = intArrayOf(-1, 1, 0, 0)
    val nearCol = intArrayOf(0, 0, -1, 1)


    val queue = LinkedList<Point14442>()
    queue.offer(Point14442(0, 0, 0))
    visited[0][0][0] = 1

    while (queue.isNotEmpty()) {
        val now = queue.poll()
        if (now.row == n - 1 && now.col == m - 1) {
            println(visited[now.row][now.col][now.brokenNumber])
            return
        }

        for (i in 0..<4) {
            val nr = now.row + nearRow[i]
            val nc = now.col + nearCol[i]

            if (nr !in 0..<n || nc !in 0..<m) continue

            if (visited[nr][nc][now.brokenNumber] == 0) {
                if (board[nr][nc] == 0) {
                    visited[nr][nc][now.brokenNumber] = visited[now.row][now.col][now.brokenNumber] + 1
                    queue.offer(Point14442(nr, nc, now.brokenNumber))
                } else if (board[nr][nc] == 1 && now.brokenNumber < k && visited[nr][nc][now.brokenNumber + 1] == 0) { //&& now.brokenNumber < k
                    visited[nr][nc][now.brokenNumber + 1] = visited[now.row][now.col][now.brokenNumber] + 1
                    queue.offer(Point14442(nr, nc, now.brokenNumber + 1))
                }
            }
        }
    }
    println(-1)
}


private data class Point14442(val row: Int, val col: Int, val brokenNumber: Int)








