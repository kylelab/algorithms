import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Problem : https://www.acmicpc.net/problem/2748
 * TimeComplexity:
 * Algorithim: 재귀?
 * Solution
 * 피보나치 수 2
 *
 * 재귀로 아래 수식을 구현하면 되지 않나??
 * 시간 제한에 걸릴 수 도 있으므로 캐시를 사용하자
 * F(n) = F(n-1) + F(n-2)(n>=2)
 *
 * n이 46이상이면 Int 범위를 넘어간다.
 * n이 92이상이면 Long 범위를 넘어간다.
 */

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    br.close()

    val cache = mutableMapOf<Int, Long>()
//    println(fibonacci(n, cache))
//    println(fibonacci2(n))
//    println(fibonacci3(n, cache))
    println(fibonacci4(n, cache))
}

// 하향식 방법// cache 사용
private fun fibonacci(n: Int, cache: MutableMap<Int, Long>): Long {
    if (n == 0) {
        return 0
    }

    if (n == 1) {
        return 1
    }

    val cacheValue = cache.getOrDefault(n, -1)
    if (cacheValue > 0) {
        return cacheValue
    } else {
        val newValue = fibonacci(n - 1, cache) + fibonacci(n - 2, cache)
        cache[n] = newValue
        return newValue
    }
}

// 가장 기본 방법
private fun fibonacci2(n: Int): Long {
    if (n == 0) {
        return 0
    }

    if (n == 1) {
        return 1
    }

    return fibonacci2(n - 1) + fibonacci2(n - 2)
}

// 상향식 방법1
private fun fibonacci3(n: Int, cache: MutableMap<Int, Long>): Long {
    cache[0] = 0
    cache[1] = 1

    for (i in 2..n) {
        cache[i] = cache.getOrDefault(i - 1, 0) + cache.getOrDefault(i - 2, 0)
    }
    return cache.getOrDefault(n, 0)
}

// 상향식 방법2
private fun fibonacci4(n: Int, cache: MutableMap<Int, Long>): Long {
    cache[0] = 0
    cache[1] = 1
    for (i in 0..n) {
        cache[i + 1] = cache.getOrDefault(i + 1, 0) + cache.getOrDefault(i, 0)
        cache[i + 2] = cache.getOrDefault(i + 2, 0) + cache.getOrDefault(i, 0)
    }
    return cache.getOrDefault(n, 0)
}









