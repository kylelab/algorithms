import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/**
 * Problem : https://www.acmicpc.net/problem/5427
 * TimeComplexity:
 * Algorithim: BFS
 * Solution
 * 불
 * 상근이나 불이나 똑같이 BFS를 통해서 탐색을 한다.
 *
 * 동시에 탐색하면 너무 복잡하기 때문에 불, 상근이 따로 따로 탐색한다.
 * 불이 번지는 시간(탐색차수) == 상근이가 도망가는 시간(탐색차수)이 동시에 일어난다는 점을 이용한다.
 * 불 탐색을 먼저하면서 탐색차수를 기록해놓고, 상근 탐색 차수를 불탐색차수와 비교해서 상근 탐색 차수가 더 작으면(시간이 작으면)
 * 불이 아직 오지 않은 것이므로 갈 수 있는 곳이다.
 * 불보다 상근이의 Depth가 낮은 경우에만 이동이 가능ㅎ다.
 *
 * 불용 queue랑 상근이 큐랑 따로 만들어보자
 *
 * 불 위치 와 상근이 위치는 큐에 바로 넣어서 탐색하면 편하다.
 *
 *
 *
 *
 */

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val tcCount = br.readLine().toInt()

    val nearRow = intArrayOf(-1, 1, 0, 0)
    val nearCol = intArrayOf(0, 0, -1, 1)

    val sb = StringBuilder()
    repeat(tcCount) {
        val (m, n) = br.readLine().split(" ").map { it.toInt() }

        val visitedFire: Array<IntArray> = Array(n) { IntArray(m) { 0 } }
        val visitedSanggeun: Array<IntArray> = Array(n) { IntArray(m) { 0 } }

        val fireQueue = LinkedList<Point5427>()
        val sanggeunQueue = LinkedList<Point5427>()

        for (i in 0..<n) {
            val row = br.readLine().map { it.toString() }
            for (j in 0..<m) {
                val element = row[j]
                when (element) {
                    "#" -> {
                        visitedFire[i][j] = -1
                        visitedSanggeun[i][j] = -1
                    }

                    "*" -> {
                        fireQueue.offer(Point5427(i, j))
                        visitedFire[i][j] = 1
                    }

                    "@" -> {
                        sanggeunQueue.offer(Point5427(i, j))
                        visitedSanggeun[i][j] = 1
                    }
                }
            }
        }

        // fire 탐색 먼저
        while (fireQueue.isNotEmpty()) {
            val now = fireQueue.poll()

            for (i in 0..3) {
                val nr = now.row + nearRow[i]
                val nc = now.col + nearCol[i]
                if (nr !in 0..<n || nc !in 0..<m) continue
                if (visitedFire[nr][nc] == 0) {
                    visitedFire[nr][nc] = visitedFire[now.row][now.col] + 1
                    fireQueue.offer(Point5427(nr, nc))
                }
            }
        }

        var isEscaped = false
        // 불을 피해서 상근이 탐색
        while (sanggeunQueue.isNotEmpty()) {
            val now = sanggeunQueue.poll()
            // 탈출 조건
            // 테두리에 도착했는지 확인
            // row가 0번째 아니면 n-1번째
            // col이 0번째 아니면 m-1번째
            if (isArriveExit(now.row, now.col, n, m)) {
                sb.append(visitedSanggeun[now.row][now.col])
                sb.append("\n")
                isEscaped = true
                break
            }

            for (i in 0..3) {
                val nr = now.row + nearRow[i]
                val nc = now.col + nearCol[i]
                if (nr !in 0..<n || nc !in 0..<m) continue
                if (visitedSanggeun[nr][nc] != 0) continue
                if (visitedFire[nr][nc] == 0    // 불이 방문 안한 곳이 어디지.. 이게 가능성이 있나???
                    || visitedFire[nr][nc] > visitedSanggeun[now.row][now.col] + 1 // +1해주는 이유는 옮겨갈 곳이니까 now보다 +1 depth이기 때문이다.
                ) {
                    visitedSanggeun[nr][nc] = visitedSanggeun[now.row][now.col] + 1
                    sanggeunQueue.offer(Point5427(nr, nc))
                }
            }
        }

        if (!isEscaped) {
            sb.append("IMPOSSIBLE")
            sb.append("\n")
        }

    }
    br.close()

    println(sb.toString())
}

private fun isArriveExit(row: Int, col: Int, n: Int, m: Int): Boolean {
    return row == 0 || row == n - 1 || col == 0 || col == m - 1
}

private data class Point5427(val row: Int, val col: Int)





