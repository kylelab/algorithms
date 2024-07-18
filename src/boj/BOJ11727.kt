import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Problem : https://www.acmicpc.net/problem/11727
 * TimeComplexity:
 * Algorithim: 재귀/ 동적계획법
 * Solution
 * 2*N 타일링
 *
 * 문제를 쪼개서 생각한다.
 * 점화식을 구한다.
 * f(1)이나 예제를 보고 제일 작은 경우의 결과를 구한다.
 *
 */

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    br.close()

    val cache = mutableMapOf<Int, Long>()
//    println(answer(n, cache))
    println(answer2(n, cache))
}

private fun answer(n: Int, cache: MutableMap<Int, Long>): Long {
    cache[1] = 1
    cache[2] = 3
    for (i in 3..n) {
        cache[i] = (cache.getOrDefault(i - 1, 0) + cache.getOrDefault(i - 2, 0) + cache.getOrDefault(i - 2, 0)) % 10007
    }
    return cache.getOrDefault(n, 0)
}


private fun answer2(n: Int, cache: MutableMap<Int, Long>): Long {
    if (n == 1) return 1
    if (n == 2) return 3

    val value = cache.getOrDefault(n, -1)
    if (value > -1) return value

    cache[n] = (answer2(n - 1, cache) + answer2(n - 2, cache) + answer2(n - 2, cache)) % 10007
    return cache.getOrDefault(n, 0)
}








