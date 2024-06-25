import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Problem : https://www.acmicpc.net/problem/15654
 * TimeComplexity: O(N!) = N*(N-1)*(N-2)...
 * Algorithim: 브루트 포스/ 재귀/ 순열
 * Solution
 * 1 - N까지 자연수 중에서 중복없이 M개를 고르는 순열
 *
 *
 * 1 위치에는 n개가 올 수 있고
 * 2 위치에는 1번째 위치에 있는 것도 올 수 없으므로 n-1개씩

 * selected boolean array
 * - 필요함 중복이 없어야 하므로
 *
 * a Int array
 * 실제로 선택한 배열
 *
 * 후보 배열
 *
 *
 * count -> 몇개가 왔는지 확인
 *
 * 종료 조건
 * 갯수가 m개 와 같으면 a array 출력하고 종료
 */

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val intArray = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    intArray.sort()
    br.close()

    val a: IntArray = IntArray(m)
    val selected: BooleanArray = BooleanArray(n)

    /**
     * index: 선택한 수의 index, 선택하면 한개씩 증가
     * start: 이전에 선택한 수부터 선택결정권 부여
     */
    fun go(
        index: Int,
        n: Int,
        m: Int,
    ) {
        if (index == m) {
            println(a.joinToString(" "))
            return
        }

        for (i in 0 until n) {
            if (selected[i]) continue

            selected[i] = true
            a[index] = intArray[i]
            go(index + 1, n, m)
            selected[i] = false
        }
    }

    go(0, n, m)
}
