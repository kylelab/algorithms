package nadongbin

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.min

/**
 * 효율적인 화폐 구성
 *
 *    0  1  2  3  4  5  6  7  8  9
 * 2 -1 -1  1 -1  2 -1  3
 * 3 -1 -1  1  1  2  2  2
 *
 *
 * 점화식
 * dp[15] = min(dp[13]+1, dp[12]+1)
 * dp[i-coin]을 만드는 방법이 존재하는 경우-> dp[i] = min(dp[i-coin[j] +1 , dp[i])
 * dp[i-coin]을 만드는 방법이 존재하지 않는 경우 -> dp[i] = INF
 * 화폐 단위 n 마다 위 공식으로 각 금액마다 만들 수 있는 지 없는 지 확인 해야하므로 시간복잡도는 O(n*m)이 된다.
 * 1<=N<=100 1<=M<=10000 N * M이 1,000,000 백만이므로 시간초과 없이 가능하다.
 *
 * 초기값
 * dp[i] = INF -> 화폐단위로 만들 수 있는 최대값 +1 -> 화폐단위로 만들 수 없는 최대값이다.
 * dp[coin[j]] = 1
 *
 * dp[0] = 0 으로 하면 화폐단위를 1씩 갱신할 필요가 없어진다.
 * 왜냐면 점화식에 따라서 dp[i-coin] + 1 이면 dp[2 - 2] + 1 = 1 이 되기 때문이다.
 * 0원은 아무 동전도 사용을 안하면 만들 수 있기 때문에 0이다.
 *
 * for문에서 시작을 coin으로 하는 이유는 coin으로 만들수 있는 금액의 최소 단위가 1이고, 그 coin이전은 만들 수 없는 금액이기 때문에 점화식을 돌필요가 없다.
 * 그런데 이렇게 하려면 입력받은 코인이 m보다 작은 경우만 받아야 할듯?
 *
 */
private const val INF = 10001

fun main() {
//    solution1()
    solution2()


}

private fun solution1() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m) = br.readLine().split(" ").map { it.toInt() }

    val coins = IntArray(n)
    val dp = IntArray(m + 1) { INF }
    repeat(n) { index ->
        val coin = br.readLine().toInt()
        coins[index] = coin
        if (coin <= m) {
            dp[coin] = 1
        }
    }

    for (coin in coins) {
        for (i in coin..m) {
            dp[i] = min(dp[i - coin] + 1, dp[i])
        }
    }

    if (dp[m] == INF) {
        println(-1)
    } else {
        println(dp[m])
    }
}

private fun solution2() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m) = br.readLine().split(" ").map { it.toInt() }

    val coins = IntArray(n)
    val dp = IntArray(m + 1) { INF }
    repeat(n) { index ->
        val coin = br.readLine().toInt()
        coins[index] = coin
    }

    dp[0] = 0
    for (coin in coins) {
        for (i in coin..m) {
            dp[i] = min(dp[i - coin] + 1, dp[i])
        }
    }

    if (dp[m] == INF) {
        println(-1)
    } else {
        println(dp[m])
    }
}

