import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.min

/**
 * Problem : https://www.acmicpc.net/problem/2294
 * TimeComplexity:
 * Algorithim:
 * Solution
 * 동전 2
 *
 * k원을 만드는 동전의 최소 개수를 구하기
 * dp[k] = k원을 만드는 동전의 최소 개수
 * dp[1] ~ dp[k-1]을 미리 알고 있다면
 * dp[15] = dp[10] + 1
 * dp[j] = dp[j - coin[i]] + 1
 *
 * 초기 값:
 * dp[i] -> 각 동전으로 만들 수 있는 갯수마다 1
 * -> 최소 갯수이므로 k가 가질 수 있는 최대값 + 1
 *
 *    1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
 * 1  1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
 * 5  1 2 3 4 1 2 3 4 5 2  3  4  5  6  7
 * 12 1 2 3 4 1 2 3 4 5 2  3  1
 * dp[i][j] = min(dp[i-1][j], dp[i][j-coin[i]]+1)
 * dp[j] = min(dp[j], dp[j-coin[i]]+1)
 *
 */

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, k) = br.readLine().split(" ").map { it.toInt() }

    val dp = IntArray(k + 1) { 10001 }
    val coins = IntArray(n)
    repeat(n) { index ->
        val coin = br.readLine().toInt()
        coins[index] = coin
        if (coin <= k) {
            dp[coin] = 1
        }
    }

    for (coin in coins) {
        for (j in 1..k) {
            if (j - coin < 0) continue
            dp[j] = min(dp[j], dp[j - coin] + 1)
        }
    }

    if (dp[k] == 10001) {
        println(-1)
    } else {
        println(dp[k])
    }
}


