
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

/**
 * Problem : https://www.acmicpc.net/problem/15651
 * TimeComplexity: O(N의 M승)
 * Algorithim: 브루트 포스/ 재귀/
 * Solution
 * 1 - N까지 자연수 중에서 중복상관없이 M개를 고르는 수열
 *
 * 1 위치에는 n개가 올 수 있고
 * 2 위치에는 1번째 위치에 있는 것도 올 수 있으므로 n개가 올 수 있음
 *
 *
 * selected boolean array
 * - 필요없음 체크 안해도 됨
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
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    br.close()

    val a: IntArray = IntArray(m)

    /**
     * index: 선택한 수의 index
     */
    fun go(
        index: Int,
        n: Int,
        m: Int,
    ) {
        if (index == m) {
            bw.write(a.joinToString(" ") + "\n")
            return
        }

        for (i in 1..n) {
            a[index] = i
            go(index + 1, n, m)
        }
    }

    go(0, n, m)
    bw.flush()
    bw.close()
}
