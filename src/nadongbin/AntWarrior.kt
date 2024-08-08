package nadongbin

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

/**
 * 개미 전사
 * 연속해서 선택할 수 없음, 바로 옆에있는 것은 선택할 수 없음
 *
 * dp[i] = 개미 전사가 얻을 수 있는 식량의 최댓값
 *
 * dp[i] 를 선택하는 경우
 * dp[i-1]를 선택못함 그러므로 dp[i] + dp[i-2]가 된다.
 *
 * dp[i]를 선택하지 못하는 경우
 * dp[i-1]이 선택되어 있으므로 dp[i-1]이 된다.
 *
 * 초기값
 * dp[0] = 0번째가 됨
 * dp[1] = 연속으로 선택할 수 없으므로, 0, 1번째 둘중에 최대 값이 dp[1]이 된다.
 *
 * 점화식
 * max(dp[i-1], dp[i-2]+dp[i])
 *
 */
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val storages = br.readLine().split(" ").map { it.toInt() }.toIntArray()


    val dp = IntArray(n)

    dp[0] = storages[0]
    dp[1] = max(storages[0], storages[1])

    for (i in 0..<n) {
        if (i - 2 >= 0) {
            dp[i] = max(dp[i - 1], dp[i - 2] + storages[i])
        }
    }

    println("--------------------")
    println(storages.joinToString())
    println("--------------------")
    println(dp.joinToString())

    println(dp[n - 1])
}
