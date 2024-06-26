import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Problem : https://www.acmicpc.net/problem/15650
 * TimeComplexity: O(N!) = N*(N-1)*(N-2)...
 * Algorithm: 브루트 포스/ 재귀/
 * Solution
 * 1 - N까지 자연수 중에서 중복없이 M개를 고르는 "오름차순" 수열
 *
 * 1 위치에는 n개가 올 수 있고
 * 2 위치에는 1번째 위치에 있는 것은 오지 못하기 때문에 n-1개가 올 수 있음
 * 오름차순: 다음 위치에 오는 수는 이전에 선택한 다음 수부터 선택하도록 해야한다.
 *
 * selected boolean array
 * - 중복없이니까 그전에 사용했던 수는 사용하면 안됨 -> 사용했는지 안했는지 체크하는 배열
 * 필요없음 -> 이전에 선택한 다음 수부터 선택하기 때문에 중복될 수 없음
 *
 * a Int array
 * 실제로 선택한 배열
 *
 * count -> 몇개가 왔는지 확인
 *
 * 종료 조건
 * 갯수가 m개 와 같으면 a array 출력하고 종료
 */

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    br.close()

    go(0, 1, n, m)
}

private val a: IntArray = IntArray(10)

/**
 * index: 선택하는 수의 위치
 * n: 총 갯수
 * m: 선택 갯수
 */
private fun go(
    index: Int,
    start: Int,
    n: Int,
    m: Int,
) {
    if (index == m) {
        println(a.filter { it > 0 }.joinToString(" "))
        return
    }

    for (i in start..n) {
        a[index] = i // index는 선택하는 수의 위치, i는 선택한 수가 된다. i는 1-n 중의 자연수 이므로
        go(index + 1, i + 1, n, m)
    }
}
