package nadongbin

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.min

/**
 * 효율적인 화폐 구성
 *
 * dp[i] = i원이 되기 위한 최소 화폐 개수
 *
 * dp[15] = min(dp[15-2] +1, dp[15-3] +1)
 *
 * dp[i] = min(dp[i-coin[j]] +1, dp[i])
 *
 *
 * 초기값
 * dp[0] = 0 // 아무 화폐도 사용하지 않아야 0을 만들 수 있으므로
 * dp[j] = INF(무한)값설정 j가 최대 10000이므로 10001로 만들면 된다.
 *
 * dp[coin] = 1 -> 이건 할필요가 없다. coin만큼 이중루프를 돌면서 갱신하기 때문에
 *
 *

 *
 */
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m) = br.readLine().split(" ").map { it.toInt() }

    val coins = IntArray(n)
    val dp = IntArray(m + 1) { Int.MAX_VALUE }
    repeat(n) { index ->
        val coin = br.readLine().toInt()
        coins[index] = coin
    }

    dp[0] = 0

    for (coin in coins) {
        for (i in coin..m) {
            // dp[i - coin]을 만드는 방법이 존재하지 않는 경우
            if (dp[i - coin] == Int.MAX_VALUE) continue

            dp[i] = min(dp[i - coin] + 1, dp[i])
        }
    }

    if (dp[m] == Int.MAX_VALUE) {
        println(-1)
    } else {
        println(dp[m])
    }


}
