import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Problem : https://www.acmicpc.net/problem/11047
 * TimeComplexity:
 * Algorithim:
 * Solution
 * 동전 0
 *
 * 동전의 최소 개수를 구하기
 *
 * 동전 종류를 큰것부터 작은 거 까지 나누기를 하면 될듯?
 *
 * K/Ai > 0 -> 몫을 더한다.
 * K%Ai로 반복한다.
 */

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, k) = br.readLine().split(" ").map { it.toInt() }

    val coins = IntArray(n)
    repeat(n) { index ->
        coins[index] = br.readLine().toInt()
    }

    var target = k
    var ans = 0
    for (i in n - 1 downTo 0) {
//        val count = target / coins[i]
//        if (count > 0) {
//            ans += count
//            target %= coins[i]
//        }

        if (target >= coins[i]) {
            ans += target / coins[i]
            target %= coins[i]
        }
    }

    println(ans)
}


