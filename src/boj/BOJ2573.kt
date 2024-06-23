import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/**
 * Problem : https://www.acmicpc.net/problem/2573
 * TimeComplexity:
 * Algorithim:  DFS, BFS
 * Solution
 *
 * 1년 지날때마다 빙산 녹이기
 *   실제 배열에 반영하면 계산 결과가 다음 빙산에 영향을 미치기때문에 따로 관리 해야하고
 *   따로 관리를 n*n 행렬로 만들면 낭비이기때문에 리스트로 만들자
 *   그리고 0일때마다 바다로 행렬과 리스트에 반영한다.
 *   녹일때는 리스트를 돌면서 행렬의 4방향을 확인해서 줄인다.
 *   리스트를 한번더 돌면서 0보다 작으면 이중행렬과 리스트에 반영
 *
 * 빙산이 따로 떨어졌는지 확인하기
 *   DFS로 순회하면서 4
 *
 *
 */

private lateinit var sea: Array<IntArray>
private lateinit var visited: Array<BooleanArray>

private val nearX = intArrayOf(0, 0, -1, 1)
private val nearY = intArrayOf(-1, 1, 0, 0)


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    sea = Array(n) {
        br.readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    visited = Array(n) { BooleanArray(m) { true } }
    val iceList: MutableList<Ice> = mutableListOf()

    for (i in 0 until n) {
        for (j in 0 until m) {
            val height = sea[i][j]
            if (height > 0) {
                iceList.add(Ice(i, j, height))
            }

        }
    }

    var year = 1
    while (iceList.isNotEmpty()) {
        for (ice in iceList) {
            // 인접한 곳에 물이 있는지 확인
            for (i in 0..3) {
                val nx = nearX[i] + ice.row
                val ny = nearY[i] + ice.col
                if (sea[nx][ny] == 0) {
                    ice.height--
                }
            }
        }


        val iterator = iceList.iterator()
        while (iterator.hasNext()) {
            val ice = iterator.next()
            if (ice.height <= 0) {
                sea[ice.row][ice.col] = 0

                iterator.remove()
            } else {
                visited[ice.row][ice.col] = false
            }
        }

        if (iceList.size > 0) {
            val dfs = dfs(iceList[0].row, iceList[0].col)
            if (dfs != iceList.size) {
                println(year)
                return
            }
        }

        year++
    }
    println(0)
}

private fun dfs(row: Int, col: Int): Int {
    visited[row][col] = true
    var cnt = 1

    for (i in 0..3) {
        val nx = nearX[i] + row
        val ny = nearY[i] + col

        if (visited[nx][ny]) continue
        cnt += dfs(nx, ny)
    }
    return cnt
}

private fun bfs(row: Int, col: Int): Int {
    val queue = LinkedList<Ice>()
    queue.offer(Ice(row, col, 0))
    visited[row][col] = true

    var cnt = 0
    while (queue.isNotEmpty()) {
        val ice = queue.poll()
        cnt++

        for (i in 0..3) {
            val nx = nearX[i] + ice.row
            val ny = nearY[i] + ice.col

            if (visited[nx][ny]) continue
            queue.offer(Ice(nx, ny, 0))
            visited[nx][ny] = true
        }
    }
    return cnt
}

data class Ice(val row: Int, val col: Int, var height: Int)
