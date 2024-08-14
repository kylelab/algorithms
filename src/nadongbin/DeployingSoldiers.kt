package nadongbin

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

/**
 * 병사 배치 하기
 *
 * 가장 긴 증가하는 부분 수열(Longest Increasing Subsequence, LIS)로 해결할 수 있다.
 * dp[i]: 각 요소를 끝으로 하는 끝으로 하는 가장 긴 증가하는 부분 수열의 길이를 저장
 * 초기화: 모든 요소는 1로 초기화
 *  자기 자신만 있을때 부분 수열길이 1이 있으므로
 *
 * 각 요소에 대해 이전 요소들을 탐색하여, 현재요소가 이전 요소보다 크면 dp 배열을 업데이트 한다.
 *
 * if(dp[i]>dp[j])
 * dp[i] = max(dp[i], dp[j]+1)
 *
 *arr[i]를 끝으로 하는 LIS를 찾기 위해, arr[0]부터 arr[i-1]까지의 모든 요소를 검사합니다.
 * 만약 arr[i]가 arr[j]보다 크다면, arr[j]를 끝으로 하는 LIS에 arr[i]를 추가할 수 있습니다.
 * 이때, dp[j] + 1은 arr[i]를 끝으로 하는 새로운 LIS의 길이가 됩니다.
 * arr[j]를 끝으로 하는 LIS의 길이에 arr[i]를 추가한 것이므로 길이가 1 증가합니다.
 *
 * 다만 현재 문제에서는 가장 긴 감소하는 부분 수열을 구하는 것이기때문에, 입력받은 배열을 뒤집어서 수행한다.
 *
 */
fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val graph = br.readLine().split(" ").map { it.toInt() }.toIntArray().reversedArray()

    val dp = IntArray(n) { 1 }
    for (i in 1..<n) {
        for (j in 0..<i) {
            if (graph[i] > graph[j]) {
                dp[i] = max(dp[i], dp[j] + 1)
            }
        }
    }

    println(dp.joinToString())

    println(n - dp.max())
}