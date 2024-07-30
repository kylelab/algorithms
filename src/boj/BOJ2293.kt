import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Problem : https://www.acmicpc.net/problem/2293
 * TimeComplexity:
 * Algorithim: DP 동적계획법
 * Solution
 * 동전 1
 *
 * 구해야하는 것은 k원이 되는 경우의 수
 *
 * dp[k] = k원이 되는 경우의 수
 * dp[1] ~ dp[k-1] 까지의 경우의 수를 알고 있다면
 *
 *   0 1 2 3 4 5 6 7 8 9 10
 * 1 1 1 1 1 1 1 1 1 1 1 1
 * 2 1 1 2 2 3 3 4 4 5 5 6
 * 5 1 1 2 2 3 4 5 6 7 8 10
 *
 * dp[i][j] = dp[i-1][j] + dp[i][j-coin[i]]
 * dp[j] = dp[j] + dp[j-coin[i]]
 * // 2차원 점화식을 1차원으로 변경하는 방법? -> i만큼 돌면서 1차원 배열에 누적하면 결국 같아진다.
 *
 * 초기값 dp[0] = 1 이 필요하다.
 */

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, k) = br.readLine().split(" ").map { it.toInt() }

    val coins = IntArray(n)
    repeat(n) { index ->
        coins[index] = br.readLine().toInt()
    }
    br.close()

    val dp = IntArray(k + 1)
    dp[0] = 1

    for (coin in coins) {
        for (j in 1..k) {
            if (j - coin < 0) continue
            dp[j] = dp[j] + dp[j - coin]
        }
    }

    println(dp[k])
}
