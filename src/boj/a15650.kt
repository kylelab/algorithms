import java.util.*

/**
 * Problem
 * TimeComplexity: O(N!) = N*(N-1)*(N-2)...
 * Algorithim: 브루트 포스/ 재귀/ 주어진 수 M개 중 N개를 선택하거나 순서(수열)대로 선택하는 경우 재귀로 풀어야하는 경우가 많음?
 * Solution
 * for문으로 1부터 N까지 돌면서 첫글자를 선택하고 다음 글자선택은 재귀에 맡긴다.
 *
 */

fun main() {
    val sc: Scanner = Scanner(System.`in`)
    val n = sc.nextInt()
    val m = sc.nextInt()
    sc.close()

    go(0, 1, n, m)
}

// 앞 index에서 사용했는지 확인 용도, 최대 n개(1부터 시작하므로 n+1)
private val c = BooleanArray(9) { false }

// 수열 저장용 최대 m개
private val a = IntArray(8)

private fun go(
    index: Int,
    start: Int,
    n: Int,
    m: Int,
) {
    // 재귀 종료 조건/ 0부터 m-1개를 선택했기때문에 m번째는 할 필요 없음
    if (index == m) {
        println(a.filter { it != 0 }.joinToString(separator = " "))
        return
    }

    for (i in start..n) {
        if (c[i]) continue // 이미 앞 index에서 사용했다면 다음 수로 넘어감

        c[i] = true
        a[index] = i
        go(index + 1, start + 1, n, m) // 오름차순이기때문에 앞의 수는 고려할 필요없기때문에 start+1부터 다시시작
        c[i] = false
    }
}
