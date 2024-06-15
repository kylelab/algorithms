
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Problem : https://www.acmicpc.net/problem/15652
 * TimeComplexity: O(N!) = N*(N-1)*(N-2)...
 * Algorithim: 브루트 포스/ 재귀/
 * Solution
 * 1 - N까지 자연수 중에서 중복상관없이 M개를 고르는 오름차순 수열
 *
 *
 * 1 위치에는 n개가 올 수 있고
 * 2 위치에는 1번째 위치에 있는 것도 올 수 있으므로 n개가 올 수 있음
 * 오름차순+ 중복허용 이기 때문에 이전 위치에서 선택한 수부터 다시 선택할 수 있다.
 *
 * selected boolean array
 * - 필요없음 오름차순이므로 그 앞쪽은 중복되지 않고, 선택한 수부터 중복가능함
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

    val a: IntArray = IntArray(m)

    /**
     * index: 선택한 수의 index, 선택하면 한개씩 증가
     * start: 이전에 선택한 수부터 선택결정권 부여
     */
    fun go(
        index: Int,
        start: Int,
        n: Int,
        m: Int,
    ) {
        if (index == m) {
            println(a.joinToString(" "))
            return
        }

        for (i in start..n) {
            a[index] = i
            go(index + 1, i, n, m)
        }
    }

    go(0, 1, n, m)
}
