import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

/**
 * Problem : https://www.acmicpc.net/problem/15654
 * TimeComplexity:
 * Algorithim: 브루트 포스/ 재귀/
 * Solution
 * n개의 수에서 M개의 수열을 뽑아낸다.
 * 중복이 허용된다.
 *
 * 선 작업
 * n개의 수 정렬
 *
 * Base case
 * index == m
 *
 * Recursive case
 * 중복이 가능하기 때문에 기존에 선택했는 지 알필요가 없다.
 * 0 until n 동안 돌면서 선택한다.
 * 선택한 수를 배열에 넣는다.
 * 선택한 수 index를 점차 늘려준다.
 * go(index)
 *
 *
 */

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val numbers = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    br.close()

    val bw = BufferedWriter(OutputStreamWriter(System.out))

    numbers.sort()
    val selectedNumbers = IntArray(m)

    fun go(index: Int) {
        if (index == m) {
            bw.write(selectedNumbers.joinToString(" ") + "\n")
            return
        }

        for (i in 0 until n) {
            selectedNumbers[index] = numbers[i]
            go(index + 1)
        }
    }

    go(0)

    bw.flush()
    bw.close()
}
