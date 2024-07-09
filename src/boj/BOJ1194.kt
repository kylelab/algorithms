import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/**
 * Problem : https://www.acmicpc.net/problem/1194
 * TimeComplexity:
 * Algorithim: BFS
 * Solution
 * 달이 차오른다 가자
 *
 * board와 visited 배열 선언하고,
 * 0과 1의 좌표를 저장해두고
 * 0은 queue에 넣는다.
 *
 *
 * 똑같이 상하좌우로 BFS 탐색하는데
 * 열쇠를 포인트 클래스에 같이 저장해서 가지고 있는다.
 *
 *
 *
 */

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val (n, m) = br.readLine().split(" ").map { it.toInt() }


    val board: Array<CharArray> = Array(n) {
        br.readLine().toCharArray()
    }

    val visited: Array<Array<IntArray>> = Array(n) { Array(m) { IntArray(1 shl 6) } }

    var start: Point1194? = null
    var end: Point1194? = null
    for (i in 0..<n) {
        for (j in 0..<m) {
            val now = board[i][j]
            when (now) {
                '0' -> start = Point1194(i, j, 0)
                '1' -> end = Point1194(i, j, 0)
            }
        }
    }
    if (start == null || end == null) {
        println(-1)
        return
    }

    val nearRow = intArrayOf(-1, 1, 0, 0)
    val nearCol = intArrayOf(0, 0, -1, 1)

    val key = arrayOf('a', 'b', 'c', 'd', 'e', 'f')
    val door = arrayOf('A', 'B', 'C', 'D', 'E', 'F')

    val queue = LinkedList<Point1194>()
    queue.offer(start)
    visited[start.row][start.col][0] = 1

    while (queue.isNotEmpty()) {
        val now = queue.poll()

        for (i in 0..<4) {
            val nr = now.row + nearRow[i]
            val nc = now.col + nearCol[i]

            if (nr !in 0..<n || nc !in 0..<m) continue
            if (visited[nr][nc][now.keys] != 0) continue

            when (val nowBoard = board[nr][nc]) {
                '#' -> {
                    continue
                }

                '.', '0' -> {
                    if (visited[nr][nc][now.keys] == 0) {
                        visited[nr][nc][now.keys] = visited[now.row][now.col][now.keys] + 1
                        queue.offer(Point1194(nr, nc, now.keys))
                    }
                }

                in key -> {
                    val nextKey = now.keys or (1 shl (nowBoard - 'a'))
                    if (visited[nr][nc][nextKey] == 0) {
                        visited[nr][nc][nextKey] = visited[now.row][now.col][now.keys] + 1
                        queue.offer(Point1194(nr, nc, nextKey))
                    }
                }

                in door -> {
                    val hasKey = now.keys and (1 shl (nowBoard - 'A')) != 0
                    if (hasKey && visited[nr][nc][now.keys] == 0) {
                        visited[nr][nc][now.keys] = visited[now.row][now.col][now.keys] + 1
                        queue.offer(Point1194(nr, nc, now.keys))
                    }
                }

                '1' -> {
                    println(visited[now.row][now.col][now.keys])
                    return
                }
            }
        }
    }
    println(-1)
}


private data class Point1194(val row: Int, val col: Int, val keys: Int)








