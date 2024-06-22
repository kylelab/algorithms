import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Problem : https://www.acmicpc.net/problem/2573
 * TimeComplexity:
 * Algorithim: 브루트포스? DFS, BFS
 * Solution
 * 이중 행렬로 입력을 받고,
 * 이중 행렬을 돌면서 동서남북의 0의 갯수를 세서 1년씩 지나가도록 만들어본다.
 * 빙산이 분리가 되었는지 아닌지는 어떻게 판단할 수 있을까?
 *
 *
 */

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val bingsan = Array(n) {
        br.readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    println("=========")
    bingsan.forEach { it ->
        println(it.joinToString("   "))
    }
    println("=========")

    val nearX = intArrayOf(0, 0, -1, 1)
    val nearY = intArrayOf(-1, 1, 0, 0)

    fun afterYear() {
        for (i in 0 until n) {
            for (j in 0 until m) {
                val now = bingsan[i][j]
                if (now == 0)
                    continue

                var warterCnt = 0
                for (k in 0 until 4) {
                    val nx = nearX[k] + i
                    val ny = nearY[k] + j
                    if (nx >= 0 && nx < n && ny >= 0 && ny < m && bingsan[nx][ny] == 0) {
                        warterCnt++
                    }
                }
                var ff = now - warterCnt
                if (ff < 0) {
                    ff = 0
                }

                bingsan[i][j] = ff
            }
        }
    }

    afterYear()
    println("=========")
    bingsan.forEach { it ->
        println(it.joinToString("   "))
    }
    println("=========")

    afterYear()
    println("=========")
    bingsan.forEach { it ->
        println(it.joinToString("   "))
    }
    println("=========")
}
