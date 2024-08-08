package nadongbin

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.min

/**
 * 1로 만들기
 *
 * * 최적부분구조와 중복되는 부분 문제를 만족하기때문에 동적계획법으로 풀 수 있다.
 *
 *
 * dp[i] = 연산을 사용하는 횟수의 최소값
 * dp[6]은 min(dp[5], dp[3], dp[2]) + 1로 풀 수 있다.
 *
 * 점화식
 * dp[i] = min(dp[i-1], dp[i/5], dp[i/3], dp[i/2]) + 1
 * 5/3/2로 나눠지는 경우에만 값을 비교해야한다. 문제의 조건자체가 5/3/2로 나누어 떨어질때만이기 때문이다.
 *
 * 초기값은 dp[1] = 0 뿐이다.
 *
 *
 * dp[1] = 0
 * dp[2] = 1
 * dp[3] = 1
 * dp[4] = dp[2] + 1 = 2
 * dp[5] = 1
 *
 *
 */
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val dp = IntArray(n + 1)

    for (i in 2..n) {
        dp[i] = dp[i - 1] + 1
        if (i % 2 == 0) {
            dp[i] = min(dp[i], dp[i / 2] + 1)
        }
        if (i % 3 == 0) {
            dp[i] = min(dp[i], dp[i / 3] + 1)
        }
        if (i % 5 == 0) {
            dp[i] = min(dp[i], dp[i / 5] + 1)
        }
    }

    println("==============")
    for (i in 0..n) {
        print("$i=${dp[i]}, ")
    }
    println()
    println(dp[n])
}