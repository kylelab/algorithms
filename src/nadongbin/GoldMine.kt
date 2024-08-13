package nadongbin

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

/**
 * 금광
 * 금광의 모든 위치에서 아래 세가지만 고려하면 된다.
 * 1. 왼쪽 위에서 오는 경우
 * 2. 왼쪽에서 오는 경우
 * 3. 왼쪽 아래에서 오는 경우
 * 세가지 경우중 최대값 + 현재 위치 금 값
 *
 * Ans = max(맨 오른쪽 열)
 * dp[i][j]  = gold[i][j] + max(dp[i-1][j-1], dp[i][j-1], dp[i+1][j-1])
 *
 * 주의할 점은 이중 for문 돌때 열부터 하나씩 진행해야 한다는 것이다.
 * 그래야 초기값인 1열의 값들이 모두 채워진다.
 * 행부터 돌게 되면 첫번째 행만 먼저 채워지는데 이때 왼쪽 아래 등은 값이 비어져있는 상태일 수 있다.
 *
 */
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val t = br.readLine().toInt()
    repeat(t) {
        val (n, m) = br.readLine().split(" ").map { it.toInt() }
        val array = br.readLine().split(" ").map { it.toInt() }
        val gold = Array(n + 1) { IntArray(m + 1) }

        var arrayIndex = 0
        for (i in 1..n) {
            for (j in 1..m) {
                gold[i][j] = array[arrayIndex++]
            }
        }

        repeat(n + 1) { index ->
            println(gold[index].joinToString())
        }

        println("===============")


        val dp = Array(n + 1) { IntArray(m + 1) }

        for (j in 1..m) {
            for (i in 1..n) {
                val leftUp = dp[i - 1][j - 1]

                val left = dp[i][j - 1]

                val leftDown = if (i + 1 <= n) {
                    dp[i + 1][j - 1]
                } else {
                    0
                }

                dp[i][j] = gold[i][j] + max(leftUp, max(left, leftDown))

            }
        }

        println()
        repeat(n + 1) { index ->
            println(dp[index].joinToString())
        }


        var max = 0
        for (i in 1..n) {
            val target = dp[i][m]
            if (target > max) {
                max = target
            }
        }

        println(max)
    }

}