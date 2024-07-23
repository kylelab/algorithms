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
 * 동전의 최소 개수를 구하기
 *
 * 문제의 요구사항: K원을 만드는 동전의 최소 갯수
 * d[k]: 동전의 최소 개수
 *
 * 두가지 방식 있다.
 * 방법1 - 만들 수 있는 금액에 동전의 액수를 더해서 새로운 경우 생성
 * 이미 만든 금액을 {i}로 취급
 * dp[i + coin[j]]= min(dp[i + coin[j]], dp[i]+1)
 * dp[i] -> i를 만드는데 필요한 동전 갯수, 이미 알고 있기 때문에
 * dp[i + coin[j]] -> 기존 알고 있는 i가치에 다음 동전의 가치 coin[j]를 더한 것의 동전 갯수
 * dp[i]+1 -> dp[i]는 이미 알고 있고, coin[j]를 더했기때문에 동전은 1개 추가됐으므로 1을 더해준다.
 * min(dp[i + coin[j]], dp[i]+1) -> min을 비교하는 것은 기존에 dp[i + coin[j]]의 알고 있는 동전 갯수가 더 적을 수 있기 때문이다.
 *
 * 방법2 -
 * 만들려는 금액을 {i}로 취급
 * dp[i] = min(dp[i], dp[i-coin[j]] + 1)
 *
 */

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, k) = br.readLine().split(" ").map { it.toInt() }

    val dp = IntArray(k + 1) { 100001 }

    val coins = IntArray(n)
    repeat(n) { index ->
        coins[index] = br.readLine().toInt()
        if (coins[index] <= k) {
            dp[coins[index]] = 1
        }
    }

    // 방법 1
    for (i in 1..k) {
        for (j in 0..<n) {
            if (i + coins[j] <= k) {
                dp[i + coins[j]] = min(dp[i + coins[j]], dp[i] + 1)
            }
        }
    }

    // 방법 2
//    for (i in 1..k) {
//        for (j in 0..<n) {
//            if (i - coins[j] >= 0) {
//                dp[i] = min(dp[i], dp[i - coins[j]] + 1)
//            }
//        }
//    }

    if (dp[k] == 100001) {
        println(-1)
    } else {
        println(dp[k])
    }

}


