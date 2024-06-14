
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Problem : https://www.acmicpc.net/problem/2747
 * TimeComplexity:
 * Algorithim: 브루트 포스/ 재귀
 * Solution
 * n번째의 피보나치 수를 리턴하라
 *
 * Base Case(종료조건, 계산하지 않아도 아는 값)
 * n==1 || n==2 -> return 1
 *
 * Recursive Case(문제를 쪼개서 생각해보자)
 * fibo(n) = fibo(n-1) + fibo(n-2)
 *
 * 중복되는 연산이 많으므로, cache해보자
 *
 */

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    br.close()

    val cache = IntArray(n + 1) { 0 }

    fun fibo(n: Int): Int {
        if (n == 1 || n == 2) {
            return 1
        }
        if (cache[n] != 0)
            {
                return cache[n]
            }

        cache[n] = fibo(n - 1) + fibo(n - 2)
        return cache[n]
    }

    val ans = fibo(n)
    println(ans)
}
