
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Problem https://www.acmicpc.net/problem/9095
 * TimeComplexity:
 * Algorithim: 브루트 포스/ 재귀/
 * Solution
 * 브루트 포스 문제는 방법의 개수가 최대 몇개인지 계산해봐야한다.
 * 정수 n을 1,2,3의 합으로 나타낸다.
 * 각 자리마다 3개(1,2,3)가 올 수 있으므로 3n승보다 작거나 같다.
 * n<=10 이므로 3의 10승(59049)보다 작거나 같으므로 경우의 수가 많지않으므로 모든 방법을 다 만들어본다.
 * 재귀함수로 풀때는 기준을 만들어줘야한다.
 * 1. 위치(각각의 위치에서 올 수 있는 수)이거나 2. 수(사용한다 안한다.)를 기준으로 하는 방법이 있다.
 * 기준으로 무엇을 해야하는 지 결정하는 것이 함수다.
 * cnt: 사용한 수의 갯수
 * sum: 합
 * go(cnt, sum, n)
 * 현재 호출한 시점의 사용한 갯수는 count고 이것으로 합이 sum이다.
 *
 * 1..다음 경우의 수일때 값이 어떻게 변하는지 모두 알아야한다.
 * 1. 1을 사용함: go(count+1, sum+1, n)
 * 2. 2를 사용함: go(count+1, sum+2, n)
 * 3. 3을 사용함: go(count+1, sum+3, n)
 *
 * 2.. 정답을 찾은 경우
 * sum == n
 *
 * 3.. 불가능한 경우
 * 문제의 조건을 위배
 * 절대로 답을 구할 수 없는 경우
 *  sum>n
 */

fun main() {
    val br: BufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val testCaseCount = br.readLine()?.toInt() ?: 0
    if (testCaseCount <= 0) {
        return
    }
    val testCases: IntArray = IntArray(testCaseCount)
    for (i in 0 until testCaseCount) {
        testCases[i] = br.readLine()?.toInt() ?: 0
    }
    br.close()

    testCases.forEach { it ->
        println(go(0, 0, it))
    }
}

fun go(
    cnt: Int,
    sum: Int,
    n: Int,
): Int {
    if (sum == n) {
        return 1
    }
    if (sum > n) {
        return 0
    }

    var ans = 0
    for (i in 1..3) {
        ans += go(cnt + 1, sum + i, n)
    }
    return ans
}

/**
 * 이렇게 하면 TC마다 0으로 초기화 해줘야하니까 위 로직이 더 깔끔핟.
 */
var ans = 0

fun go2(
    cnt: Int,
    sum: Int,
    n: Int,
) {
    if (sum == n) {
        ans++
        return
    }
    if (sum > n) {
        return
    }

    for (i in 1..3) {
        go(cnt + 1, sum + i, n)
    }
}
