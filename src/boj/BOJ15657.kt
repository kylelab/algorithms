import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Problem : https://www.acmicpc.net/problem/15654
 * TimeComplexity:
 * Algorithim: 브루트 포스/ 재귀/
 * Solution
 * n개의 수에서 M개의 수열을 선택한다.
 * 중복가능
 * 오름차순
 *
 * 선 작업
 * 입력 배열정렬한다.
 *
 * Base case
 * index == m
 *
 * Recursive case
 * index: 선택한 수의 인덱스, 선택한 수가 늘때마다(재귀 호출) +1을 한다.
 * 중복은 가능하지만 오름 차순이어야함
 * 즉 기존 수는 선택가능하지만 그 전 수들은 선택하면 안된다.
 * n 배열을 0부터 도는 것이아니라 start부터 돈다.
 * start until n
 * start: n 배열 돌기 시작하는 인덱스, 중복이 가능하므로 다음 재귀 호출때는 +1이아닌 start부터 다시 돈다.
 *
 * go(index, start)
 *
 *
 */

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val numbers = br.readLine().split(" ").map { it.toInt() }.sorted().toIntArray()

    val selectedNumbers = IntArray(m)
    val sb = StringBuilder()

    fun go(index: Int, start: Int) {
        if (index == m) {
            sb.append(selectedNumbers.joinToString(" ")).append("\n")
            return
        }

        for (i in start until n) {
            selectedNumbers[index] = numbers[i]
            go(index + 1, i)
        }
    }

    go(0, 0)
    println(sb.toString())
}
