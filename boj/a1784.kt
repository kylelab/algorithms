import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Problem: https://www.acmicpc.net/problem/1784
 * Algorithm: 브루트 포스(건너 뛰며 찾기)
 * TimeComplexity: O(logN)
 * Solution
 * 1 - 9 : 1 자리 (9 - 1 + 1) * 1 9
 * 10 - 99 : 2 자리 (99 - 10 + 1) * 2 90
 * 100 - 999: 3 자리 (999 - 100 + 1) * 3 900
 *
 * len=1 부터 시작, 자릿수
 * start=1 부터 시작, *10씩 건너뛴다.file
 * end = start*10-1
 *
 * end가 n을 넘어가면 n으로 대체
 *
 * ans는 (end-start+1)*len을 계속 더해준다.
 *
 */
fun main() {
    val br: BufferedReader = BufferedReader(InputStreamReader(System.`in`))

    val n = br.readLine().toInt()
    br.close()

    println(getDigits2(n))
}

fun getDigits2(n: Int): Int  {
    var len = 1
    var start = 1
    var end = 0
    var ans = 0
    while (start <= n) {
        end = start * 10 - 1

        if (end > n) {
            end = n
        }

        ans += (end - start + 1) * len

        start *= 10
        len++
    }

    return ans
}
